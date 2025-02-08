# Application Setup Guide

This document provides the steps to clone, build, and run the application using Docker. It also includes instructions for accessing the Swagger UI and testing the API endpoints.

## Steps to Run the Application

### 1. Clone the repository

Clone the repository using Git:

```bash
git clone https://github.com/JuananMtez/small-ecommerce.git
```

### 2. Navigate to the project directory
```bash
cd small-ecommerce
```

### 3. Build the code
```bash
./gradlew build
```

### 4. Build the Docker image
```bash
docker build -t app .
```

### 5. Run the application with Docker
```bash
docker run -p 8080:8080 app
```


### 6. Access Swagger UI
[Swagger](http://localhost:8080/swagger-ui/index.html)

### 7. Log in with the username and password

 - Username: user
 - Password: The password will be shown in the terminal when you start the Spring Boot application.

### 8. Test the endpoints
Once logged in, you can start testing the available endpoints via the Swagger UI. You will find a list of all available endpoints and be able to interact with them.