# Student Management System

A Spring Boot 3 project for managing student records with JWT authentication, validation, analytics endpoints, built-in documentation, and a browser dashboard.

## Overview

This project demonstrates a complete backend-driven student management workflow:

- secure login with JWT token generation
- protected CRUD APIs for student records
- course filtering, topper lookup, and statistics endpoints
- a browser dashboard at `/ui`
- built-in project documentation at `/docs`
- MySQL-backed persistence for student records
- H2-supported automated test setup

## Features

- JWT-secured API flow
- Spring Security integration
- Spring Data JPA persistence layer
- Bean validation with readable error responses
- dashboard-style UI using Thymeleaf
- demo data loader for quick presentation
- automated integration tests with MockMvc
- cloud-friendly startup using `PORT`

## Tech Stack

- Java 17
- Spring Boot 3.3.5
- Spring Security
- Spring Data JPA
- Thymeleaf
- MySQL
- H2
- Maven Wrapper

## Demo Credentials

- Username: `admin`
- Password: `1234`

## Local Run

Run the app from the project root:

```powershell
$env:MAVEN_USER_HOME="$PWD\\.m2"
.\mvnw.cmd spring-boot:run
```

Open:

- Home: `http://localhost:8080/`
- Docs: `http://localhost:8080/docs`
- Dashboard: `http://localhost:8080/ui`

## Test the Project

```powershell
$env:MAVEN_USER_HOME="$PWD\\.m2"
.\mvnw.cmd test
```

## API Endpoints

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/` | Project overview and demo details |
| `POST` | `/auth/login` | Generate JWT token |
| `GET` | `/students` | Get all students |
| `GET` | `/students/{id}` | Get student by id |
| `GET` | `/students/course/{course}` | Filter students by course |
| `GET` | `/students/topper` | Get top-scoring student |
| `GET` | `/students/stats` | Get student statistics |
| `POST` | `/students` | Add a student |
| `PUT` | `/students/{id}` | Update a student |
| `DELETE` | `/students/{id}` | Delete a student |
| `GET` | `/ui` | Open dashboard |
| `GET` | `/docs` | Open built-in documentation |

## Sample Requests

Login:

```json
{
  "username": "admin",
  "password": "1234"
}
```

Create student:

```json
{
  "name": "Kiran Rao",
  "course": "Computer Science",
  "marks": 94
}
```

Use the token in protected requests:

```http
Authorization: Bearer <your-token>
```

## Database Setup

This project is configured to use MySQL `studentdb` for normal app runs, so student records are stored in the MySQL database.

Default local settings:

- Database: `studentdb`
- Username: `root`
- Password: `piyush123`

If your MySQL setup is different, provide:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`
- `DB_DRIVER=com.mysql.cj.jdbc.Driver`
- `JPA_DIALECT=org.hibernate.dialect.MySQLDialect`

## Deployment

This repository is prepared for simple cloud deployment.

- `render.yaml` is included for Render
- the app respects the `PORT` environment variable
- tests still run with an isolated H2 test profile

Detailed deployment and sharing content:

- [DEPLOYMENT.md](./DEPLOYMENT.md)
- [LINKEDIN_POST.md](./LINKEDIN_POST.md)

## Project Structure

```text
src/main/java/com/piyush/studentapp
|- config
|- controller
|- dto
|- exception
|- model
|- repository
|- security
```

## Demo Flow

1. Open `/` and explain the project.
2. Open `/docs` and show the documented endpoints.
3. Open `/ui` and show the dashboard.
4. Call `/auth/login` with the demo user.
5. Use the token on `/students`.
6. Show `/students/course/{course}`, `/students/topper`, and `/students/stats`.
7. Create, update, and delete a student live.

## Future Improvements

- role-based authorization
- student attendance or marks history
- CSV or PDF export
- user registration flow
- hosted production database
