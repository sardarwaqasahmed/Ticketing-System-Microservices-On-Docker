# Login Service Api (Waqas Ahmed)

Its an API for authentication the services. It has following Endpoints:

- /api/auth GET call to list all registered users
- /api/auth POST call get the JWT token after entering username and password

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

- On APIs startup, H2 DB will execute *data.sql* scripts for initial data insertion.
  
- Default User is inserted as ordersrv / ordersrv and ticketsrv / ticketsrv which will be used in Login Service.

- For running as mvn project user following command

- mvn clean install test
  
- For running on docker , you have to map the port of 8081 as this is the api port.
  
- Swagger UI for API documentation is accessible from : 
http://localhost:8081/loginsrv/swagger-ui.html
  
- For using any API service JWT token is required. First need to hit /api/auth service to get the JWT token, then use this token as an 
  Authorization header in any other request to any end point in the API, other wise you will get 
  unauthorized error.
  
# Create Docker Image

- Go to the root folder of project and issue the following command.

- docker build . -t login-service

- this will generate the docker image by reading Dockerfile

# Run Image As Container
  
- Run below command in order to run image as docker container

- docker run -d -p 8081:8081 --name loginserviceapi login-service