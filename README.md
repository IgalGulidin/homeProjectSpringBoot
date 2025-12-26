# Poll System – Backend (Spring Boot)



This project implements a **backend poll system** using **Java, Spring Boot, and H2**.

The system is built as **two independent services** that communicate via REST APIs.



No frontend is included.



---



## Architecture



### User Service

- Port: **8081**

- Manages users (customers)

- Stores user data

- Calls Poll Service when a user is deleted



### Poll Service

- Port: **8082**

- Manages polls and answers

- Stores poll statistics

- Validates users via User Service



Each service uses its **own H2 database**.



---



## Technologies

- Java 17

- Spring Boot

- Spring Web

- Spring JDBC (JdbcTemplate)

- Spring Cloud OpenFeign

- H2 Database

- Gradle



---



## User Service API (8081)



### Create User

**POST** `/user/create`



```json

{

&nbsp; "firstName": "Igal",

&nbsp; "lastName": "Gulidin",

&nbsp; "email": "igal@test.com",

&nbsp; "age": 25,

&nbsp; "address": "Tel Aviv"

}

