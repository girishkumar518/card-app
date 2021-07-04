# Cards Application

Cards application is microservice developed using below frameworks 
Used spring boot reactive programiing . Reactive Streams are implemented using WebFlux Reactive API 

Apis are designed using REST Principles 

Cards Application is Reactive RESTful Webservice

- Spring Boot 2.5.2
- Spring 5 Web Reactive 
- Spring Data R2DBC ( Supports Postgresql , Mysql , H2 ..etc ) 
- Actuator
- Embedded H2 Database
- Swagger 2.0
- Javax Validation Framework
- Mapper Framework
- Lambock Framework
- Junit 5, Project Reactor  and Mockito Framework for Unit and Integration Tests 
- Maven
- Java 8

### To run the application 

- Build the application  `maven clean install` from the root directory
- cd into  `target ` folder 
- run command to execute spring boot app  `java -jar cards-app-1.0.jar `


### Run the application using docker
- install docker on your local machine / server
- Build the application  `maven clean install` from the root directory
- Build Docker image / skip to take image from docker hub `docker build -t <image-name> . ` ex: `docker build -t cards-app .`
- Check image created  `docker images`
- Take docker image from docker repo and run `docker run -p localMachinePort:imageExposePort girishkumar518/cards-app` ex: `docker run -p 8080:8080 girishkumar518/cards-app` 


### Useful links

- Swagger URL - http://localhost:8080/swagger-ui/#
- api-docs - http://localhost:8080/v2/api-docs
- Health - http://localhost:8080/actuator/health









