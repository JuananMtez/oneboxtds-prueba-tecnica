FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/small-ecommerce-0.0.1-SNAPSHOT.jar /app/small-ecommerce.jar

EXPOSE 8080

# Ejecuta la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "small-ecommerce.jar"]