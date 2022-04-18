# Config-Server (Waqas Ahmed)

Spring Cloud Config-Server is used for managing resources as per different environment. i.e Local, DEV, PRD

# Technologies And Tools Used:

* Java 1.8
* Spring Cloud COnfig, Spring Boot
* Maven as a build Tool
* Docker

# Description And Run Setup

- Once server startd successfully each api profile as per different environment can be accessed as below.
  http://localhost:1111/config-server/loginsrvapi-dev.yml
  http://localhost:1111/config-server/loginsrvapi-local.yml
  
  http://localhost:1111/config-server/ordersrvapi-dev.yml
  http://localhost:1111/config-server/ordersrvapi-local.yml
  
- For running as mvn project use following command

- mvn clean install test
  
- For running on docker , you have to map the port of 1111 as this is the config-server port.

  
# Create Docker Image

- Go to the root folder of project and issue the following command. For example for config-server

- docker build . -t config-server

- this will generate the docker image by reading Dockerfile

- For individually creating docker image repeat the above command for each individual service

# Run Image As Container
  
- Run below command in order to run image as docker container

- docker run -d -p 1111:1111 --name configserver config-server
