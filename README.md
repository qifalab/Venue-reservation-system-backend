# Venue Reservation System Backend

> **Overview**  
> This project is a backend service for a venue reservation system built with **Java 17** and **Spring Boot**, using **Maven** with the **Maven Wrapper** for builds, **Spring Data JPA** for data persistence, **Hibernate Validator** for request validation, and **springdoc-openapi** to generate **Swagger UI** documentation. It supports H2, MySQL, and PostgreSQL databases and follows the standard Maven directory structure with versioned RESTful APIs.

---

## Project Description
This repository contains the backend code for the “Venue-reservation-system-backend” service. It uses **Spring Boot** to quickly set up a microservices architecture and provides CRUD APIs for venues and reservations.

---

## Features
- **Venue Management**: Create, read, update, and delete venue information  
- **Reservation Management**: Create, query, and cancel venue reservations  
- **Validation**: Request parameter validation using Hibernate Validator annotations  
- **Error Handling**: Global exception handling with a unified response format  
- **API Documentation**: Automatic OpenAPI 3.0 documentation with Swagger UI  
- **Extensibility**: Easily add authentication, authorization, and other plugins  

---

## Tech Stack
- **Java 17+**  
- **Spring Boot 3.x**  
- **Spring Data JPA** (Jakarta Persistence API)  
- **Hibernate Validator** (JSR-303/JSR-380)  
- **springdoc-openapi** (Swagger UI integration)  
- **Maven & Maven Wrapper** (`mvnw`, `mvnw.cmd`, `.mvn/wrapper`)  
- **Databases**  
  - H2 (in-memory/embedded)  
  - MySQL  
  - PostgreSQL  

---

## Requirements
1. JDK 17 or higher  
2. Git client  
3. Internet access to Maven Central  

---

## Installation & Running

```bash
# Clone the repository
git clone https://github.com/qifalab/Venue-reservation-system-backend.git

# Enter the project directory
cd Venue-reservation-system-backend

# Build, test, and package with Maven Wrapper
./mvnw clean package

# Run the application
./mvnw spring-boot:run
