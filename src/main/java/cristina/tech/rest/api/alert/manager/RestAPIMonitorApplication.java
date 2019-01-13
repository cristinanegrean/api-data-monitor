package cristina.tech.rest.api.alert.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RestAPIMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestAPIMonitorApplication.class, args);
	}

}

