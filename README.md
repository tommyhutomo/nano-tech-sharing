# Nano chaining Technical sharing

This repo meant to be use for STD tech sharing

## How to start
```
mvn eclipse:eclipse
mvn springboot:run
```
## How it work

![alt text](https://github.com/tommyhutomo/moneylion-tech-assesment/blob/master/img/img.png?raw=true)

## Important URL

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 console : http://localhost:8080/h2-console
- Hytrix Stream : http://localhost:8080/actuator/hystrix.stream
- Health check : http://localhost:8080/actuator/health
- Metrics : http://localhost:8080/actuator/metrics

All other Actuator endpoints are active

## Note
- Some validation error used custom error object derived from RuntimeException, kindly scroll down to see the actual message
