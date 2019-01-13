package cristina.tech.rest.api.alert.manager.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Data
@Slf4j
public class Channel {

    private String url, authToken;

    public boolean isValid() {
        if (!StringUtils.hasText(url) || !StringUtils.hasText(authToken)) {
            log.error("Invalid alerting channel config! One of mandatory fields (url|authToken) are empty or missing");
            return false;
        }

        return true;
    }
}
