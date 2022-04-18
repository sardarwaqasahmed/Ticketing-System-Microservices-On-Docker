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
  
# Create Docker Image One By One

- Go to the root folder of project and issue the following command. For example for config-server

- docker build . -t config-server

- this will generate the docker image by reading Dockerfile

- For individually creating docker image repeat the above command for each individual service

# Run Image As Container One By One
  
- Run below command in order to run image as docker container

- docker run -d -p 1111:1111 --name configserver config-server


# Use docker-compose tool for running all the container once

- Run below command in order to run all docker images once. All images will be pulled from docker trusted registry and run as container.
- docker-compose up -d


# All Microserivces Docker Image Are Uploaded On DockerHub
![DockerHub-Registry](https://user-images.githubusercontent.com/9270627/163886879-5a451a99-2aa8-4b23-a680-dfc096f7493b.png)


# After Successfull Run You Can View Docker Container As Following:
![Micro-Services](https://user-images.githubusercontent.com/9270627/163887052-b26d2252-45a2-48bb-a3c7-cf4f6fa7780b.png)

# For Testing [Login-Service] Using Swagger UI
![Login-API](https://user-images.githubusercontent.com/9270627/163887440-1baebf74-01f9-45a9-911e-ce8307824a5e.png)


# For Testing [Order-Service] Using Swagger UI
![OrderAPI](https://user-images.githubusercontent.com/9270627/163887261-adad5f0e-8c8a-49cb-af68-9e9c35b295d3.png)

# For Testing [Ticket-Service] Using Swagger UI
![TicketAPI](https://user-images.githubusercontent.com/9270627/163887312-0c0b60b1-de90-468c-84c2-05c058ea3996.png)




