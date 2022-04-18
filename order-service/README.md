# OrderServoce Api (Waqas Ahmed)

Its an API for managing OrderDelivery. Api contains following EndPoints:

- CRUD for OrderDelivery 
- /api/v1/search 
- /api/v1/search/{deliveryStatus}

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
  
- Default Orders are created with minimal data.

- For running as mvn project user following command

- mvn clean install test
  
- For running on docker , you have to map the port of 8080 as this is the api port.
  
- Swagger UI for API documentation is accessible from : 
http://localhost:8080/ordersrv/swagger-ui.html
  
- For using any API service JWT token is required. First need to hit /v1/login service and pass ordersrv as username and password to get the JWT token, then use this token as an Authorization header in any end point of the API, other wise you will get  unauthorized error.
  
# Create Docker Image

- Go to the root folder of project and issue the following command.

- docker build . -t order-service

- this will generate the docker image by reading Dockerfile

# Run Image As Container
  
- Run below command in order to run image as docker container

- docker run -d -p 8080:8080 --name order-serviceapi order-service