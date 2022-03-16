# phone-validation-server
This is the server side application for phone validation services

## Description

This project is mainly for the server-side App for phone-validation application where it spins up on docker image to serve locally on port 8080

JDK 1.7

Spring Boot

JPA
## Run server

Run `docker build --pull --rm -f "Dockerfile" -t phonevalidationserver:latest "."` for adding needed packages.

Run `docker-compose up` for running the image.


N.B : If Build fails for any reason , you can just run this command first to build the jar file


Run `./mvnw clean package` for project build

Run `docker build --pull --rm -f "Dockerfile" -t phonevalidationserver:latest "."` for adding needed packages.

Run `docker-compose up` for running the image.

# Getting Started

APIController is the entry point where you will find the following apis


`api/health` : checking that server is up and running

`api/countries` : for getting countries list

`api/customers` : for getting customers data
### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.4/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.4/reference/htmlsingle/#using-boot-devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.4/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

