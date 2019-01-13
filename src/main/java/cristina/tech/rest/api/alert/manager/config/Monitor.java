package cristina.tech.rest.api.alert.manager.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Data
@Slf4j
public class Monitor {

    private String url, method, body, jsonPath, alertText;
    private int statusCode;

    public boolean isValid() {
        if (StringUtils.isEmpty(url) || StringUtils.isEmpty(method) || StringUtils.isEmpty(alertText)) {
            log.error("Invalid monitor config! One of mandatory fields (url|method|alertText) are empty or missing.");
            return false;
        }

        if (!StringUtils.hasText(jsonPath) && statusCode == 0) {
            log.error("Invalid monitor config! Both 'jsonPath' and HTTP 'statusCode' fields are empty or missing.");
            return false;
        }

        HttpMethod httpMethod = HttpMethod.valueOf(method);
        if (httpMethod == POST && StringUtils.isEmpty(body)) {
            log.error("Invalid monitor config! You configured POST Api with empty body, cannot make monitoring request.");
            return false;
        }

        if (httpMethod != GET && httpMethod != POST) {
            log.error("Monitor only supports for now HTTP GET and POST.", method);
            return false;
        }

        return true;
    }

}
