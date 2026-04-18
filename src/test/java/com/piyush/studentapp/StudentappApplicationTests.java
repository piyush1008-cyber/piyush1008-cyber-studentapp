package com.piyush.studentapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.piyush.studentapp.model.Student;
import com.piyush.studentapp.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class StudentappApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository.deleteAll();

        studentRepository.save(createStudent("Demo Student", "Computer Science", 88));
        studentRepository.save(createStudent("Priya Singh", "Computer Science", 95));
        studentRepository.save(createStudent("Arjun Nair", "Mechanical", 76));
    }

    @Test
    void homeEndpointShouldReturnProjectOverview() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.project").value("Student Management System"))
                .andExpect(jsonPath("$.availableEndpoints", hasSize(12)))
                .andExpect(jsonPath("$.documentation.projectDocs").value("/docs"))
                .andExpect(jsonPath("$.documentation.dashboard").value("/ui"));
    }

    @Test
    void dashboardPageShouldLoad() throws Exception {
        mockMvc.perform(get("/ui"))
                .andExpect(status().isOk());
    }

    @Test
    void loginShouldReturnJwtTokenForValidCredentials() throws Exception {
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "username": "admin",
                                  "password": "1234"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty());
    }

    @Test
    void studentsEndpointShouldRejectRequestWithoutToken() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(status().isForbidden());
    }

    @Test
    void studentsEndpointShouldReturnStudentsSortedByMarks() throws Exception {
        String token = fetchToken();

        mockMvc.perform(get("/students")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name").value("Priya Singh"))
                .andExpect(jsonPath("$[0].marks").value(95));
    }

    @Test
    void courseSearchShouldReturnMatchingStudents() throws Exception {
        String token = fetchToken();

        mockMvc.perform(get("/students/course/Computer Science")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Priya Singh"));
    }

    @Test
    void topperEndpointShouldReturnBestStudent() throws Exception {
        String token = fetchToken();

        mockMvc.perform(get("/students/topper")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Priya Singh"))
                .andExpect(jsonPath("$.marks").value(95));
    }

    @Test
    void statsEndpointShouldReturnAggregateInformation() throws Exception {
        String token = fetchToken();

        mockMvc.perform(get("/students/stats")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalStudents").value(3))
                .andExpect(jsonPath("$.highestMarks").value(95))
                .andExpect(jsonPath("$.lowestMarks").value(76))
                .andExpect(jsonPath("$.averageMarks", closeTo(86.33, 0.01)));
    }

    @Test
    void addStudentShouldValidateMarks() throws Exception {
        String token = fetchToken();

        mockMvc.perform(post("/students")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Invalid Student",
                                  "course": "Civil",
                                  "marks": 120
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.marks").value("Marks must be at most 100"));
    }

    @Test
    void updateAndDeleteStudentShouldWorkWithToken() throws Exception {
        String token = fetchToken();
        Integer studentId = studentRepository.findAll().get(0).getId();

        mockMvc.perform(put("/students/{id}", studentId)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Updated Student",
                                  "course": "Information Technology",
                                  "marks": 92
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Student"))
                .andExpect(jsonPath("$.marks").value(92));

        mockMvc.perform(delete("/students/{id}", studentId)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Student deleted with id " + studentId));
    }

    private Student createStudent(String name, String course, int marks) {
        Student student = new Student();
        student.setName(name);
        student.setCourse(course);
        student.setMarks(marks);
        return student;
    }

    private String fetchToken() throws Exception {
        String response = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "username": "admin",
                                  "password": "1234"
                                }
                                """))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode jsonNode = objectMapper.readTree(response);
        return jsonNode.get("token").asText();
    }
}
