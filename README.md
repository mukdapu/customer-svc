## README #

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

* Customer microservice

### How do I get set up? ###

* This application requires Java 17 for compiling and running. The maven build generates a jar file. It can be run with
  embedded server or can be run as a docker container.
* find pom.xml and use any IDE (Spring suite, Eclipse, Intelij)
* Configuration

The default active profile is dev. Using -e in docker, set appropriate profile based on target environment. The
following environment properties can be set to customize the environment. Make sure that these values are set all the
environments or in staging and production environment as these environments do not have default values for security
reasons.

- **DB_URL**: Set the value to override default uri: jdbc:mysql://localhost:3306/customer.
- **SERVER_PORT**: The default port is 8100. Set this property to change the app server port.

* Dependencies

Maven pom file has all the required dependencies defined. Build can be kicked off without any prerequisite. All it needs
is Java 17 and Maven installed in the build server.

* Database configuration

This application uses MySQL DB. The development was done with MySQL 8

* How to run tests

  `mvn clean install` or `mvn test`

* Deployment instructions

### How to set up customer application? ###

**1)  Download and install MySQL DB:**

**2) To run Integration Test we use H2 DB.

### Contribution guidelines ###

* Writing tests
* Code review

### Who do I talk to? ###


