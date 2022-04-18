# AutoTicketCron-Service Api (Waqas Ahmed)

This is an automated background job system. Its used Feign Client in order to read orders data and insert ticket data in ticket sytem.
Following are the 2 Scheduler:

- MoveOrderToKitchenScheduler
- RaiseTicketScheduler 

# Technologies And Tools Used:

* Java 1.8
* Spring WEB, Spring Boot, Spring Data
* Feign Client
* Junit, Mockito
* Spring Security with JWT Token
* Database H2 (In-Memory DB).
* Maven as a build Tool
* OpenAPI V3 with swaggerUI 
* Docker

# Description And Run Setup

- On APIs startup, it will run 2 scheduler that will br responsible for below task.

	# MoveOrderToKitchenScheduler
- This Background Job Will Fetch All Order That Are Being Placed By End User. This Scheduler Is Responsible For Moving Orders To The Kitchen. Status WIll Be Updated From 'Received' To 'Preparation'.
- Expected Delivery Time Is Also Being Updated On This Stage.
  
	# RaiseTicketScheduler
- If The Expected Time Of Delivery Is Passed And The Order Is Still Not Delivered, Its Priority Automatically Becomes Higher Then Others.
- An Auto Ticket Is Raised In The System So That Agent Can Follow Up And Expedite The Food Preparation Process.	
  
# Create Docker Image

- Go to the root folder of project and issue the following command.

- docker build . -t autoticketcron-service

- this will generate the docker image by reading Dockerfile

# Run Image As Container
  
- Run below command in order to run image as docker container

- docker run -d -p 8083:8083 --name autoticketcron-serviceapi autoticketcron-service