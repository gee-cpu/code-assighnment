Code Assignment Application
This is a simple Spring Boot application that provides REST APIs for basic mathematical operations like addition, subtraction, multiplication, and division.

Getting Started
Prerequisites
To run this application, you need to have Java 17 or higher and Maven installed on your machine.

Installing
Clone this repository to your local machine
Navigate to the root directory of the project
Run mvn clean install command to build the project
Run java -jar target/code-assighnment-0.0.1-SNAPSHOT.jar command to start the application
Running the Tests
To run the unit tests, execute the following command in the project root directory:

bash
Copy code
mvn clean test
API Endpoints
The following are the available REST API endpoints for the application:

Operation
Endpoint	HTTP Method	Request Body	Description
/addition	POST	{"num1":10,"num2":20}	Adds two numbers and returns the result
/subtraction	POST	{"num1":10,"num2":20}	Subtracts two numbers and returns the result
/multiplication	POST	{"num1":10,"num2":20}	Multiplies two numbers and returns the result
/division	POST	{"num1":10,"num2":20}	Divides two numbers and returns the result

calcUser Endpoints:

GET /api/users: retrieve a list of all users
GET /api/users/{id}: retrieve a single user by id
POST /api/users: create a new user
PUT /api/users/{id}: update an existing user by id
DELETE /api/users/{id}: delete a user by id

userRecord Endpoints:
GET /api/records: retrieve a list of all records
GET /api/records/{id}: retrieve a single record by id
POST /api/records: create a new record
PUT /api/records/{id}: update an existing record by id
DELETE /api/records/{id}: delete a record by id

Configuration
The following application properties can be configured in the application.properties file:

Property	Default Value	Description
server.port	8080	Port number for the application to listen on
operation.addition.cost	0.50	Cost of performing addition operation
operation.subtraction.cost	0.75	Cost of performing subtraction operation
operation.multiplication.cost	1.00	Cost of performing multiplication operation
operation.division.cost	1.50	Cost of performing division operation
Built With
Spring Boot - Framework to create stand-alone, production-grade Spring-based applications
Maven - Dependency Management 
Lombok

Authors
George - https://github.com/gee-cpu