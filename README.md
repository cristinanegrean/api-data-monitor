## Scheduled task (crontab) application to monitor REST API response and send alerts

Demonstrated concepts:

* Scheduled task / crontab job with Spring
* Configure basic monitors and alerting channels in yaml (customizable external config)
* Load yaml as Java POJOs with Spring 
* Send monitor API REST calls with Spring's RestTemplate
* Map String API call response as JsonNode with Jackson JSON 
* Check if a monitored configurable JsonNode is present and has data (aka monitored API is healthy)
* If monitor API becomes unhealthy, post a message to a Slack channel using Spring's RestTemplate

Usage:
* Build docker image, upload to DockerHub
* Mount the ConfigMaps as the volume inside the Spring Boot application Docker container
* Build Helm chart and load Spring Boot application.yaml via Kubernetes ConfigMaps
* Install Helm chart in service Kubernetes cluster
