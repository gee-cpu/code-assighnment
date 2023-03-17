Arithmetic REST calculator 


### Environment dependencies

Java 17   
Apache Maven 3.x  


### General information

This is  a simple calculator functionality with operations  (addition, subtraction,
multiplication, division, square root, and a random string generation) where each functionality has
 a separate cost per request.
User’s will have a starting credit/balance. Each request will be deducted from the user’s balance.
If the user’s balance isn’t enough to cover the request cost, the request shall be denied.



### Technology stack overview

* [Lombok](https://projectlombok.org/) - annotate entities with @Data instead of writing getters, setters and
  standard methods as toString equals and hashCode
  spring-boot-devtools
* Spring boot



### Getting Started

**Step 1. Dev environment setup**.

**Step 2**. Clone this repository

**Step 3**. Compile and launch your app by running:
`mvn clean install`

### IDE configuration
I used Java 17, make sure  JDK 17 is installed.    
Install Apache Tomcat    
Maven should be 3.5.1 and up

Import project in Intellij IDEA.   
  
You will need a Lombok plugin for your IDE.
Authors
George - https://github.com/gee-cpu