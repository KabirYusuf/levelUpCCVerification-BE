# Credit Card Validation Backend

The Credit Card Validation Backend is a Java Spring Boot application designed by Yusuf Kabir Adekunle, email: kabiryusuf2307@gmail.com. It provides a RESTful API for validating credit card information. The application uses Java 17, Spring Boot 3.1.3, and follows Test-Driven Development (TDD) principles with JUnit 5.9.3 for testing functionality.

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
- cd levelUpCCVerification-BE
- mvn clean install

macOS/Linux (Run the following commands on your terminal):
- cd levelUpCCVerification-BE
- ./mvnw clean install

Make sure your system is connected to the internet as Maven will download required dependencies.

### Running the Application
You can run the Spring Boot application using the following Maven command on your terminal:

Windows:
- mvn spring-boot:run

macOS/Linux:
- ./mvnw spring-boot:run

You can also run the Credit Card Validation Backend using popular Integrated Development Environments (IDEs) like IntelliJ IDEA and Eclipse.

The application will start locally on port 8080.

## Testing
All functionalities in this application have been tested using JUnit 5.9.3. To run the tests, you can use the following Maven command:
The test file can be found inside the test directory "src/test/java/com/levelup/levelUpCCVerificationBE/service/LevelUpCreditCardValidationTest.java"

Windows:
- mvn test

macOS/Linux:
- ./mvnw test

## API Documentation
The API provides a single endpoint for validating credit card information:

- Endpoint: localhost:8080/api/v1/credit-card/validate/
- Method: POST
- Request Body: JSON object with three properties: expiryDate, cvv, and cardNumber.
- Example Request Body:
{
"expiryDate":"10/25",
"cvv": "123",
"cardNumber":"4417123456789113"
}

ApiResponse

The application's RESTful API returns responses in the form of an `ApiResponse` object. This object consists of two main properties:

### `isSuccessfulRequest`

- Type: `boolean`
- Description: Indicates whether the request was successful or if an exception was thrown during processing.
- Values:
  - `true`: The request was successful, and no exceptions were encountered.
  - `false`: An exception occurred during the request.

### `data`

- Type: `string`
- Description: Contains a string message that indicates the validity of the credit card.
- Format: The message is in the following format: `"Is card valid? : " + isValidCard`
- Values:
  - `"Is card valid? : true"`: Indicates that the credit card is valid.
  - `"Is card valid? : false"`: Indicates that the credit card is not valid.

You can use these properties to determine the success of your requests and check the validity of credit cards when working with the API.

For example, if `isSuccessfulRequest` is `true`, you can check the value of `data` to determine if the credit card is valid or not.


## Supported Card Types
The application supports the validation of the following card types:

- Visa
- Mastercard
- Discover
- JCB
- Diners Club and Carte Blanche

## Exception Handling
This application handles exceptions gracefully. Invalid input data or format will result in appropriate exception responses.



