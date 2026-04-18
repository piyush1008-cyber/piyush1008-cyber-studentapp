# Student Management System

A polished Spring Boot project for managing student records with JWT-based authentication, validation, a browser dashboard, and a MySQL database.

## Highlights

- JWT-secured student CRUD APIs
- Spring Boot 3 + Spring Security + Spring Data JPA
- Input validation with clear error responses
- MySQL database integration
- Sample student records loaded automatically at startup
- Integration tests for login and protected endpoints
- Browser dashboard for live demo

## Tech Stack

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL Database
- Thymeleaf frontend

## Demo Login

- Username: `admin`
- Password: `1234`

## API Endpoints

- `GET /` : Project overview and demo information
- `POST /auth/login` : Generate JWT token
- `GET /students` : Get all students
- `GET /students/course/{course}` : Get students from a course
- `GET /students/topper` : Get highest scoring student
- `GET /students/stats` : Get student analytics
- `GET /students/{id}` : Get student by id
- `POST /students` : Add a student
- `PUT /students/{id}` : Update a student
- `DELETE /students/{id}` : Delete a student
- `GET /ui` : Open interactive dashboard
- `GET /docs` : Open built-in project documentation page

## How To Run

1. Open the project in your IDE.
2. Run `StudentappApplication`.
3. Visit `http://localhost:8080/` to show the project overview.
4. Open `http://localhost:8080/docs` to show professional project documentation.
5. Open `http://localhost:8080/ui` to show the live browser dashboard.
6. Use `POST http://localhost:8080/auth/login` with the demo credentials.
7. Copy the returned token and send it as:

```http
Authorization: Bearer <your-token>
```

8. Call the `/students` APIs.

## Sample Login Request

```json
{
  "username": "admin",
  "password": "1234"
}
```

## Sample Add Student Request

```json
{
  "name": "Kiran Rao",
  "course": "Computer Science",
  "marks": 94
}
```

## MySQL Setup

By default, the project is configured for:

- Database: `studentdb`
- Username: `root`
- Password: `piyush123`

If your MySQL setup is different, provide these environment variables before running the app:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`
- `DB_DRIVER=com.mysql.cj.jdbc.Driver`
- `JPA_DIALECT=org.hibernate.dialect.MySQLDialect`

## Presentation Flow

1. Show the home endpoint to explain the project.
2. Open the built-in docs page and show the documented APIs.
3. Open the dashboard at `/ui`.
4. Show the login API and generate the JWT token.
5. Show course-wise filtering, topper lookup, and student statistics.
6. Access protected student APIs with the token.
7. Add a new student live.
8. Update marks or course.
9. Delete a student and explain secured CRUD flow.

## Future Improvements

- Add role-based authorization
- Export reports for student performance
- Add user registration and profile management
