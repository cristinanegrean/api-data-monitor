package cristina.tech.rest.api.alert.manager.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "my")
@Getter
public class MonitorConfig {

    /** REST APIs to monitor for response latency and data content. */
    private final List<Monitor> monitors = new ArrayList<>();

    /** Alerting channels. */
    private final List<Channel> channels = new ArrayList<>();
}
