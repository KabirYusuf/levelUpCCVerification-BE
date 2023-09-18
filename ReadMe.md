# Credit Card Validation Backend

The Credit Card Validation Backend is a Java Spring Boot application designed by Yusuf Kabir Adekunle. It provides a RESTful API for validating credit card information. The application uses Java 17, Spring Boot 3.1.3, and follows Test-Driven Development (TDD) principles with JUnit 5.9.3 for testing functionality.

**Prerequisites:**
- Java 17 or higher
- Spring Boot 3.1.3 or higher
- Maven

## Table of Contents

- [Getting Started](#getting-started)
    - [Clone the Repository](#clone-the-repository)
    - [Install Dependencies](#install-dependencies)
    - [Running the Application](#running-the-application)
- [Testing](#testing)
- [API Documentation](#api-documentation)
- [Supported Card Types](#Supported-card-types)
- [Exception Handling](#exception-handling)

## Getting Started

### Clone the Repository

To get started, clone this repository from GitHub using the following command on your terminal:

git clone https://github.com/KabirYusuf/levelUpCCVerification-BE.git

### Install Dependencies
Navigate to the project directory and install the necessary dependencies using Maven:

Windows (Run the following commands on your terminal):
cd levelUpCCVerification-BE
mvn clean install

macOS/Linux (Run the following commands on your terminal):
cd levelUpCCVerification-BE
./mvnw clean install

Make sure your system is connected to the internet as Maven will download required dependencies.

### Running the Application
You can run the Spring Boot application using the following Maven command on your terminal:

Windows:
mvn spring-boot:run

macOS/Linux:
./mvnw spring-boot:run

You can also run the Credit Card Validation Backend using popular Integrated Development Environments (IDEs) like IntelliJ IDEA and Eclipse.

The application will start locally on port 8080.

## Testing
All functionalities in this application have been tested using JUnit 5.9.3. To run the tests, you can use the following Maven command:

Windows:
mvn test

macOS/Linux:
./mvnw test

## API Documentation
The API provides a single endpoint for validating credit card information:

Endpoint: localhost:8080/api/v1/credit-card/validate/
Method: POST
Request Body: JSON object with three properties: expiryDate, cvv, and cardNumber.
Example Request Body:
{
"expiryDate":"10/25",
"cvv": "123",
"cardNumber":"4417123456789113"
}

## Supported Card Types
The application supports the validation of the following card types:

Visa
Mastercard
Discover
JCB
Diners Club and Carte Blanche

## Exception Handling
This application handles exceptions gracefully. Invalid input data or format will result in appropriate exception responses.



