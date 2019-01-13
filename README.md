## Scheduled task (crontab) application to monitor REST API response and send alerts


The app will start every minute a scheduled task to verify if a monitored API is healthy, i.e.:

```
2019-01-13 14:19:00,003 INFO  [scheduling-1] cristina.tech.rest.api.alert.manager.MonitorScheduledTask: Monitoring call for: Monitor(url=https://blox.weareblox.com/api/markets, method=GET, body=null, jsonPath=amount, alertText={ "text": "Prod alert: There is no markets pricing data: https://blox.weareblox.com/api/markets", "icon_emoji": ":fire:", "attachments": [ { "text": "Fix?", "color": "danger", "attachment_type": "alert", "actions": [ { "name": "fix", "text": "Redeploy Dataflow Runner", "type": "button" } ] } ] }, statusCode=200)
2019-01-13 14:20:00,004 INFO  [scheduling-1] cristina.tech.rest.api.alert.manager.MonitorScheduledTask: Monitoring call for: Monitor(url=https://blox.weareblox.com/api/markets, method=GET, body=null, jsonPath=amount, alertText={ "text": "Prod alert: There is no markets pricing data: https://blox.weareblox.com/api/markets", "icon_emoji": ":fire:", "attachments": [ { "text": "Fix?", "color": "danger", "attachment_type": "alert", "actions": [ { "name": "fix", "text": "Redeploy Dataflow Runner", "type": "button" } ] } ] }, statusCode=200)
2019-01-13 14:21:00,005 INFO  [scheduling-1] cristina.tech.rest.api.alert.manager.MonitorScheduledTask: Monitoring call for: Monitor(url=https://blox.weareblox.com/api/markets, method=GET, body=null, jsonPath=amount, alertText={ "text": "Prod alert: There is no markets pricing data: https://blox.weareblox.com/api/markets", "icon_emoji": ":fire:", "attachments": [ { "text": "Fix?", "color": "danger", "attachment_type": "alert", "actions": [ { "name": "fix", "text": "Redeploy Dataflow Runner", "type": "button" } ] } ] }, statusCode=200)
2019-01-13 14:22:00,004 INFO  [scheduling-1] cristina.tech.rest.api.alert.manager.MonitorScheduledTask: Monitoring call for: Monitor(url=https://blox.weareblox.com/api/markets, method=GET, body=null, jsonPath=amount, alertText={ "text": "Prod alert: There is no markets pricing data: https://blox.weareblox.com/api/markets", "icon_emoji": ":fire:", "attachments": [ { "text": "Fix?", "color": "danger", "attachment_type": "alert", "actions": [ { "name": "fix", "text": "Redeploy Dataflow Runner", "type": "button" } ] } ] }, statusCode=200)
2019-01-13 14:23:00,006 INFO  [scheduling-1] cristina.tech.rest.api.alert.manager.MonitorScheduledTask: Monitoring call for: Monitor(url=https://blox.weareblox.com/api/markets, method=GET, body=null, jsonPath=amount, alertText={ "text": "Prod alert: There is no markets pricing data: https://blox.weareblox.com/api/markets", "icon_emoji": ":fire:", "attachments": [ { "text": "Fix?", "color": "danger", "attachment_type": "alert", "actions": [ { "name": "fix", "text": "Redeploy Dataflow Runner", "type": "button" } ] } ] }, statusCode=200)
2019-01-13 14:24:00,002 INFO  [scheduling-1] cristina.tech.rest.api.alert.manager.MonitorScheduledTask: Monitoring call for: Monitor(url=https://blox.weareblox.com/api/markets, method=GET, body=null, jsonPath=amount, alertText={ "text": "Prod alert: There is no markets pricing data: https://blox.weareblox.com/api/markets", "icon_emoji": ":fire:", "attachments": [ { "text": "Fix?", "color": "danger", "attachment_type": "alert", "actions": [ { "name": "fix", "text": "Redeploy Dataflow Runner", "type": "button" } ] } ] }, statusCode=200)
2019-01-13 14:25:00,005 INFO  [scheduling-1] cristina.tech.rest.api.alert.manager.MonitorScheduledTask: Monitoring call for: Monitor(url=https://blox.weareblox.com/api/markets, method=GET, body=null, jsonPath=amount, alertText={ "text": "Prod alert: There is no markets pricing data: https://blox.weareblox.com/api/markets", "icon_emoji": ":fire:", "attachments": [ { "text": "Fix?", "color": "danger", "attachment_type": "alert", "actions": [ { "name": "fix", "text": "Redeploy Dataflow Runner", "type": "button" } ] } ] }, statusCode=200)
2019-01-13 14:26:00,005 INFO  [scheduling-1] cristina.tech.rest.api.alert.manager.MonitorScheduledTask: Monitoring call for: Monitor(url=https://blox.weareblox.com/api/markets, method=GET, body=null, jsonPath=amount, alertText={ "text": "Prod alert: There is no markets pricing data: https://blox.weareblox.com/api/markets", "icon_emoji": ":fire:", "attachments": [ { "text": "Fix?", "color": "danger", "attachment_type": "alert", "actions": [ { "name": "fix", "text": "Redeploy Dataflow Runner", "type": "button" } ] } ] }, statusCode=200)
2019-01-13 14:27:00,006 INFO  [scheduling-1] cristina.tech.rest.api.alert.manager.MonitorScheduledTask: Monitoring call for: Monitor(url=https://blox.weareblox.com/api/markets, method=GET, body=null, jsonPath=amount, alertText={ "text": "Prod alert: There is no markets pricing data: https://blox.weareblox.com/api/markets", "icon_emoji": ":fire:", "attachments": [ { "text": "Fix?", "color": "danger", "attachment_type": "alert", "actions": [ { "name": "fix", "text": "Redeploy Dataflow Runner", "type": "button" } ] } ] }, statusCode=200)
2019-01-13 14:28:00,006 INFO  [scheduling-1] cristina.tech.rest.api.alert.manager.MonitorScheduledTask: Monitoring call for: Monitor(url=https://blox.weareblox.com/api/markets, method=GET, body=null, jsonPath=amount, alertText={ "text": "Prod alert: There is no markets pricing data: https://blox.weareblox.com/api/markets", "icon_emoji": ":fire:", "attachments": [ { "text": "Fix?", "color": "danger", "attachment_type": "alert", "actions": [ { "name": "fix", "text": "Redeploy Dataflow Runner", "type": "button" } ] } ] }, statusCode=200)
```

In the example above there is only one API monitor with its own alert message formatted based on [Slack Message Builder](https://api.slack.com/docs/messages/builder?msg=%7B%22text%22%3A%22Prod%20alert%3A%20There%20is%20no%20markets%20pricing%20data%3A%20https%3A%2F%2Fblox.weareblox.com%2Fapi%2Fmarkets%22%2C%22icon_emoji%22%3A%22%3Afire%3A%22%2C%22attachments%22%3A%5B%7B%22text%22%3A%22Fix%3F%22%2C%22color%22%3A%22danger%22%2C%22attachment_type%22%3A%22alert%22%2C%22actions%22%3A%5B%7B%22name%22%3A%22fix%22%2C%22text%22%3A%22Redeploy%20Dataflow%20Runner%22%2C%22type%22%3A%22button%22%7D%5D%7D%5D%7D)

Demonstrated concepts:

* Scheduled task / crontab job with Spring
* Configure basic monitors and alerting channels in yaml (customizable external config)
* Load yaml as Java POJOs with Spring 
* Send monitor API REST calls with Spring's RestTemplate
* Map String API call response as JsonNode with Jackson JSON 
* Check if a monitored configurable JsonNode is present and has data (aka monitored API is healthy)
* If monitor API becomes unhealthy, post a message to a Slack channel using Spring's RestTemplate


Usage:

1) Clone and build code and Docker image: 

```bash
$ git clone https://github.com/cristinanegrean/api-data-monitor.git
$ cd api-data-monitor
$ ./gradlew clean build docker
```

2) Create a `.env` externalized config file to store your alert channel web hook URL and authorization bearer token

```bash
$ cat /Users/cristinanegrean/api_data_monitor.env 
WEB_HOOK_URL=https://hooks.slack.com/services/T00000000/B00000000/XXXXXXXXXXXXXXXXXXXXXXXX
AUTH_TOKEN="put here your htoken"
```

3) Change env_file path in `docker-compose.yml` to point to location of file you created at step 2)

```    
env_file:
  - /Users/cristinanegrean/api_data_monitor.env
```

4) Start-up application

```bash
$ docker-compose up
```

Deploy on Kubernetes:
* Mount the ConfigMaps as the volume inside the Spring Boot application Docker container
* Build Helm chart and load Spring Boot application.yaml via Kubernetes ConfigMaps
* Install Helm chart in Kubernetes cluster
