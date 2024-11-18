FROM openjdk:17-jdk-slim
COPY target/starshipManager-0.0.1-SNAPSHOT.jar starshipManager.jar
ENTRYPOINT ["java","-jar","/starshipManager.jar"]
EXPOSE 8080