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