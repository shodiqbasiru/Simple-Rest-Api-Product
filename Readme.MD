# Simple Rest API For Product Management

This is a simple REST API for managing products. The API allows users to register, login, and manage products.
The API is secured with JWT authentication.

## Features
- User registration.
- User login.
- JWT authentication.
- Product management.
- Exception handling.

## Technologies Used
- Spring Boot
- Spring Security
- Spring Data JPA
- Spring Web
- PostgreSQL
- Swagger OpenAPI
- Lombok
- JWT
- Java 17
- Maven

## Project Structure

The project is structured into several folders:

- `config`: Contains the configuration classes for beans.
- `controller`: Contains the REST controllers for the API.
- `dto`: Contains the data transfer objects for the API.
- `entity`: Contains the JPA entities for the database.
- `repository`: Contains the JPA repositories for the entities.
- `service`: Contains the service classes for the API.
- `security`: Contains the classes for JWT authentication and authorization.

## Setup

1. Clone the repository.
2. Create a PostgreSQL database.
3. Update the `application.properties` file with your database configuration.
4. Run the application.
    ```bash
    mvn spring-boot:run
    ```
5. Open your browser and navigate to `http://localhost:8080/swagger-ui/index.html` to view the API documentation.

## Usage

The API exposes the following endpoints:

- `GET /api/auth/login`: Logs in a user and returns a JWT token.
- `POST /api/auth/register`: Registers a new user.
- `GET /api/auth/validate-token`: Validates a JWT token.
- `GET /api/products`: Returns a list of all products.
- `GET /api/products/{id}`: Returns a product by ID.
- `POST /api/products`: Creates a new product.
- `PUT /api/products/{id}`: Updates a product by ID.
- `DELETE /api/products/{id}`: Deletes a product by ID.
- `GET /api/user/{email}`: Returns a user by email.




