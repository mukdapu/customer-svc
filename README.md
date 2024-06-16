## README #


This README would document deliverables for the assignment

### What is this repository for? ###

* Assignment-1: Customer microservice for `Create Customers` and `Get Customers`. Refer `com.demo.customer.controller.CustomerController` class.
* Assignment-2: How to get Customers in List A, List-B and from Both. Refer `com.demo.customer.CustomerListComparisonTest` class.
* Assignment-3: Design a timecard system. Refer `timecard.pdf` in Project root folder. 

### How do I get set up Customer microservice project ? ###

* This application requires Java 17 for compiling and running. The maven build generates a jar file. It can be run with
  embedded server or can be run as a docker container.
* find pom.xml and use any IDE (Spring suite, Eclipse, Intelij)
* Configuration

Using -e in docker, modify environment variables defined in the `application.yml` to customize the environment.


- **DB_URL**: Set the value to override default uri: jdbc:mysql://localhost:3306/customer_svc_dev.
- **SERVER_PORT**: The default port is 8080. Set this property to change the app server port.

* Dependencies

Maven pom file has all the required dependencies defined. Build can be kicked off without any prerequisite. All it needs
is Java 17 and Maven installed in the build server.

* Database configuration

This application uses MySQL DB. The development was done with MySQL 8

* How to run tests (Unit and Integration)

  `mvn clean install` or `mvn test`
Note: we use H2 DB for Integration Tests. 

* Deployment instructions

### How to set up customer application? ###

**1)  Download and install MySQL DB:**
** 2) Create new database
** 3) Adjust environment values to point to the new db (URL, credentials)
** 4) Run the application using `mvn spring-boot:run` or using Docker. For Docker, create docker image using `docker build -t <tag name> .` from the project root folder  


### Contribution guidelines ###

* Writing tests
* Code review

### Who do I talk to? ###


