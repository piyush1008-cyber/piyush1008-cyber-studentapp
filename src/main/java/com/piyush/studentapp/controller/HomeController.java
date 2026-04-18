package com.piyush.studentapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {
        return Map.of(
                "project", "Student Management System",
                "status", "Running",
                "highlights", List.of(
                        "JWT secured student APIs",
                        "Spring Boot + JPA backend",
                        "Input validation and error handling",
                        "MySQL-backed user and student data",
                        "Browser-based dashboard at /ui",
                        "Advanced demo modules for roles, attendance, results, search, pagination, charts, and export"
                ),
                "demoLogin", Map.of(
                        "url", "/auth/login",
                        "username", "admin",
                        "password", "1234"
                ),
                "availableEndpoints", List.of(
                        "GET /",
                        "POST /auth/login",
                        "GET /students",
                        "GET /students/course/{course}",
                        "GET /students/topper",
                        "GET /students/stats",
                        "GET /students/{id}",
                        "POST /students",
                        "PUT /students/{id}",
                        "DELETE /students/{id}",
                        "GET /ui",
                        "GET /docs"
                ),
                "documentation", Map.of(
                        "projectDocs", "/docs",
                        "dashboard", "/ui"
                )
        );
    }
}
