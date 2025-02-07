# Application Setup Guide

This document provides the steps to clone, build, and run the application using Docker. It also includes instructions for accessing the Swagger UI and testing the API endpoints.

## Steps to Run the Application

### 1. Clone the repository

Clone the repository using Git:

```bash
git clone https://github.com/JuananMtez/oneboxtds-prueba-tecnica.git
```

### 2. Navigate to the project directory
```bash
cd oneboxtds-prueba-tecnica
```

### 3. Build the Docker image
```bash
docker build -t app .
```

### 4. Run the application with Docker
```bash
docker run -p 8080:8080 app
```


### 5. Access Swagger UI
[Swagger](http://localhost:8080/swagger-ui/index.html)

### 6. Log in with the username and password

 - Username: user
 - Password: The password will be shown in the terminal when you start the Spring Boot application.

### 7. Test the endpoints
Once logged in, you can start testing the available endpoints via the Swagger UI. You will find a list of all available endpoints and be able to interact with them.