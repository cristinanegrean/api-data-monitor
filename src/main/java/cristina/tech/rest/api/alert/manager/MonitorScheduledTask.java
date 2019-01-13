package cristina.tech.rest.api.alert.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.NullNode;
import cristina.tech.rest.api.alert.manager.config.Channel;
import cristina.tech.rest.api.alert.manager.config.Monitor;
import cristina.tech.rest.api.alert.manager.config.MonitorConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@Component
@Slf4j
public class MonitorScheduledTask {

    @Autowired
    MonitorConfig monitoringConfig;

    @Scheduled(cron = "0/60 * * * * ?")
    public void monitor() {
        monitoringConfig.getMonitors().forEach(monitor -> {
            if (monitor.isValid() && !isHealthy(monitor))  {
                log.warn("Found unhealthy API: " + monitor);
                monitoringConfig.getChannels().forEach(channel -> {
                    if (channel.isValid()) {
                        callAlert(channel, monitor);
                    }
                });
            }
        });
    }

    private boolean isHealthy(Monitor monitor) {
        ResponseEntity<String> apiResponse = callMonitor(monitor);

        if (apiResponse == null || !apiResponse.getStatusCode().is2xxSuccessful()) return false;

        int statusCode = monitor.getStatusCode();
        if (statusCode != 0 && apiResponse.getStatusCodeValue() != statusCode) return false;

        String jsonPath = monitor.getJsonPath();
        String apiResponseBody = apiResponse.getBody();
        if (StringUtils.hasText(jsonPath) && StringUtils.isEmpty(apiResponseBody)) {
            return false;
        } else if (StringUtils.hasText(jsonPath)) {
            JsonNode jsonNode = asJsonNode(apiResponseBody);
            return jsonNode != null
                    && !(jsonNode.findPath(jsonPath) instanceof MissingNode)
                    && !(jsonNode.findPath(jsonPath) instanceof NullNode);
        }

        return false;
    }

    private JsonNode asJsonNode(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readTree(jsonString);
        } catch (IOException ioE) {
            log.info("Error while mapping Json string to Json object", ioE);
        }
        return null;
    }

    private ResponseEntity<String> callMonitor(Monitor monitor) {
        HttpMethod apiMethod = HttpMethod.valueOf(monitor.getMethod());
        String apiUrl = monitor.getUrl();
        RestTemplate apiClient = new RestTemplate();
        log.info("Monitoring call for: " + monitor);

        if (apiMethod == HttpMethod.GET) {
            return apiClient.getForEntity(apiUrl, String.class);
        }
        if (apiMethod == HttpMethod.POST) {
            return apiClient.postForEntity(apiUrl, asJsonNode(monitor.getBody()), String.class);
        }
        return null;
    }

    private void callAlert(Channel channel, Monitor monitor) {
        RestTemplate apiClient = new RestTemplate();
        log.info("Sending alert to channel: " + channel);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(MediaType.parseMediaTypes(MediaType.APPLICATION_JSON.toString()));
        headers.setBearerAuth(channel.getAuthToken());

        try {
            HttpEntity<String> alert = new HttpEntity<>(monitor.getAlertText(), headers);
            ResponseEntity response = apiClient.postForEntity(channel.getUrl(), alert, String.class);
            log.info("Got response from alert channel: " + response.getStatusCodeValue());
        } catch (Exception e) {
            log.error("Failed to send alert, error is: ", e);
        }

    }

}
