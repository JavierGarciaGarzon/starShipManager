## Prerequisites
- Java 17 or higher
- Maven 3.8.0 or higher
- Docker (if using the Dockerfile)

# Getting Started

## Clone the Repository
```
git clone https://github.com/JavierGarciaGarzon/starshipManager
```
## Build the Project
To build the project, use the following Maven command:
```
mvn clean install
```
## Run the Application
You can run the application using Maven:
```
mvn spring-boot:run
```
## Running Tests
The project includes unit tests to ensure the functionality of the application. You can run the tests using:
```
mvn test
```
## Using Docker
### Build the Docker Image
To build the Docker image for the application, use the following command:
```
docker build -t starship-manager .
```
### Run the Docker Container
After building the Docker image, you can run the application inside a container:
```
docker run -p 8080:8080 starship-manager
```
This will start the application, and it will be accessible at http://localhost:8080.

## Usage
After running the application, you can access the API endpoints to fetch user payment information. For example, to fetch information about the starship with id "2", you would use:
```
GET /api/starship/2
```
## Swagger
You can see the api and the differents methods due to Swagger.
```
http://localhost:8080/swagger-ui
```
