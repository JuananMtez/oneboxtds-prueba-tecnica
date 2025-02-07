FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/oneboxtds-0.0.1-SNAPSHOT.jar /app/oneboxtds.jar

EXPOSE 8080

# Ejecuta la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "oneboxtds.jar"]