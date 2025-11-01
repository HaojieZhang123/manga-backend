# Manga Management BAckend

Backend for a full-stack Manga Management application built with **Spring Boot**, **Spring Security**, and **Thymeleaf**.
This project provides a RESTful API and a backoffice interface for managing manga, authors, genres and statuses.

**Frontend repository**: https://github.com/HaojieZhang123/manga-frontend.git

---

## Overview

This backend application allows authenticated users to manage manga-related data, such as:

- **CRUD operations** for all entities (`Manga`, `Author`, `Genre`, `Status`)
- Relational data management:
    - `Many-to-Many` relationship between `Manga` and `Author`
    - `Many-to-Many` relationship between `Manga` and `Genre`
    - `Many-to-One` relationship between `Manga` and `Status`
- Authentication and authorization using **Spring Security**
- Thymeleaf-based backoffice interface or visual management
- **RESTful API endpoints** for integration with external frontend applications
- **DTOs** for all entities to ensure clean and secure data transfer

---

## Architecture

#### Tech stack:
- Java 21
- Spring Boot
- Spring Security 6
- Spring Data JPA (Hibernate)
- Thymeleaf 
- Maven
- MySQL Database

#### Layers:
Each entity has its own:
- Repository (JPA)
- Service
- Controller (REST + Thymeleaf)
- DTO (for POST/PUT requests)

---

### Running the application

To run the application, you can use the `MangaBackendApplication` class.

The application will start on port 8080.

#### Default endpoints:

- **Manga**: `/api/manga`
- **Author**: `/api/authors`
- **Genre**: `/api/genres`
- **Status**: `/api/status`

- **Index**: `GET /api/{resource}/`
- **Show**: `GET /api/{resource}/{id}`
- **Create**: `POST /api/{resource}/`
- **Update**: `PUT /api/{resource}/{id}`
- **Delete**: `DELETE /api/{resource}/{id}`

Thymeleaf views at:

- **Manga**: `/manga`
- **Author**: `/authors`
- **Genre**: `/genres`
- **Status**: `/status`

--- 

### Authentication and Authorization

Basic Spring Security setup:

- User must login to access the backoffice
- Only authenticated users can access the backoffice
- Only users with the "ADMIN" role can have full access to all functionality
- Login form managed by Spring Security's default login page
- GET RESTful API's endpoints are accessible to everyone

--- 

### DTOs and validation

DTOs (Data Transfer Objects) are used in all POST and PUT operations to:
- Simplify request payloads
- Avoid exposing internal data
- Ensure clean and secure data transfer
- Validate data before persisting it to the database