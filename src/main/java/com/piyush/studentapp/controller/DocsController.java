package com.piyush.studentapp.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocsController {

    @GetMapping(value = "/docs", produces = MediaType.TEXT_HTML_VALUE)
    public String docs() {
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Student Management System Docs</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            margin: 0;
                            padding: 32px;
                            background: linear-gradient(135deg, #f4f7fb, #e2ecf9);
                            color: #1c2a39;
                        }
                        .container {
                            max-width: 980px;
                            margin: 0 auto;
                            background: #ffffff;
                            padding: 32px;
                            border-radius: 20px;
                            box-shadow: 0 12px 30px rgba(28, 42, 57, 0.12);
                        }
                        h1, h2 {
                            color: #12395b;
                        }
                        .badge {
                            display: inline-block;
                            padding: 8px 14px;
                            border-radius: 999px;
                            background: #12395b;
                            color: white;
                            font-size: 14px;
                            margin-bottom: 16px;
                        }
                        .grid {
                            display: grid;
                            grid-template-columns: repeat(auto-fit, minmax(260px, 1fr));
                            gap: 16px;
                            margin: 24px 0;
                        }
                        .card {
                            padding: 18px;
                            border: 1px solid #d7e3f2;
                            border-radius: 14px;
                            background: #f9fbfe;
                        }
                        code, pre {
                            font-family: Consolas, monospace;
                        }
                        pre {
                            background: #0f1720;
                            color: #eaf2ff;
                            padding: 16px;
                            border-radius: 12px;
                            overflow-x: auto;
                        }
                        ul {
                            line-height: 1.8;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="badge">College Demo Ready</div>
                        <h1>Student Management System</h1>
                        <p>A Spring Boot project with JWT authentication, secured student APIs, validation, analytics, a browser dashboard, MySQL-backed data, and fast advanced demo modules.</p>

                        <h2>Demo Login</h2>
                        <pre>{
  "username": "admin",
  "password": "1234"
}</pre>

                        <h2>Main Endpoints</h2>
                        <div class="grid">
                            <div class="card"><strong>GET /</strong><br>Project overview</div>
                            <div class="card"><strong>GET /docs</strong><br>Built-in documentation page</div>
                            <div class="card"><strong>GET /ui</strong><br>Interactive dashboard UI</div>
                            <div class="card"><strong>POST /auth/login</strong><br>Generate JWT token</div>
                            <div class="card"><strong>GET /students</strong><br>All students sorted by marks</div>
                            <div class="card"><strong>GET /students/course/{course}</strong><br>Course-wise search</div>
                            <div class="card"><strong>GET /students/topper</strong><br>Highest scoring student</div>
                            <div class="card"><strong>GET /students/stats</strong><br>Overall statistics</div>
                            <div class="card"><strong>POST /students</strong><br>Add a new student</div>
                            <div class="card"><strong>Advanced UI</strong><br>Roles, attendance, results, search, pagination, charts, export placeholders</div>
                        </div>

                        <h2>Authorization Header</h2>
                        <pre>Authorization: Bearer &lt;your-jwt-token&gt;</pre>

                        <h2>Sample Add Student Request</h2>
                        <pre>{
  "name": "Kiran Rao",
  "course": "Computer Science",
  "marks": 94
}</pre>

                        <h2>Presentation Flow</h2>
                        <ul>
                            <li>Show the home page at <code>/</code></li>
                            <li>Show this documentation page at <code>/docs</code></li>
                            <li>Open the dashboard at <code>/ui</code></li>
                            <li>Highlight the advanced module cards and chart section</li>
                            <li>Login and generate a JWT token</li>
                            <li>Show topper and statistics APIs</li>
                            <li>Add, update, and delete a student live</li>
                        </ul>
                    </div>
                </body>
                </html>
                """;
    }
}
