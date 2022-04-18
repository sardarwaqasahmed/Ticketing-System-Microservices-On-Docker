# Automated Ticketing System (Waqas Ahmed)

This system will used to improve the customer experience by automatically raising ticket before customer reports. This system contains following EndPoints:
- login-service
- order-service 
- ticket-service 
- autoticketcron-service
- cloud-config-service

# Technologies And Tools Used:

* Java 1.8
* Spring WEB, Spring Boot, Spring Data
* Junit, Mockito
* Spring Security with JWT Token
* Database H2 (In-Memory DB).
* Maven as a build Tool
* OpenAPI V3 with swaggerUI 
* Docker

# Description And Run Setup

- On APIs startup, H2 DB will execute *data.sql* scripts for initial data insertion for each api for example order-service.
  
- Default User is inserted as ordersrv / ordersrv which will be used in Login Service for getting the JWT token.

- For running as mvn project use following command

- mvn clean install test
  
- For running on docker , you have to map the port of 8080 as this is the order-service api port.
  
- Swagger UI for API documentation is accessible from : 
http://localhost:8080/ordersrv/swagger-ui.html
  
- For using any API service JWT token is required. First need to hit /loginsrv/api/auth service to get the JWT token, then use this token as an 
  Authorization header in any other request to any end point in the API, other wise you will get 
  unauthorized error.
  
# Create Docker Image

- Go to the root folder of project and issue the following command. For example for config-server

- docker build . -t config-server

- this will generate the docker image by reading Dockerfile

# Run Image As Container
  
- Run below command in order to run image as docker container

- docker run -d -p 1111:1111 --name configserver config-server
