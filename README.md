# 57BlocksCodingTest

Technical specifications:
- Java 17
- Spring
  - Spring boot
  - Spring Security
  - Spring data JPA
- Gradle
- MySql as Database

This app is containerized using Docker. In the files structure there is a Dockerfile and a docker-compose.yaml.

### To execute this:
1. Make sure you have JAVA_HOME is java 17
2. Make sure you have Docker installed
3. In the root folder of this repository open a console.
4. Run this commands
   ```
   $ ./gradlew build
   $ docker build -t springboot-app .
   $ docker compose up -d
    ```
5. After some minutes you can check if the containers are running. You can use this command
   ```
   $ docker ps
    ```
   You will see two containers springboot-app and mysql:latest.
6. To access to the developed resources please go to http://localhost:8080/swagger-ui/index.html in your local machine.

### About this project:
1. this solution is developed in a Hexagonal Architecture (Ports and adapters) in order to define clearly the responsabilities of the components of the app.
2. There are defined a set of unit tests and integration tests guaranteeing the aplication of the bussiness rules in the solution domain.
3. The URL provided before, represents the API documentation in OpenAPI, there are the definitions of the different resources developed.
