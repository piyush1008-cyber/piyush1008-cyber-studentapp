package com.piyush.studentapp.controller;

import com.piyush.studentapp.model.Student;
import com.piyush.studentapp.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository repo;

    @PostMapping
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {
        return ResponseEntity.ok(repo.save(student));
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return repo.findAllByOrderByMarksDesc();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));
    }

    @GetMapping("/course/{course}")
    public List<Student> getStudentsByCourse(@PathVariable String course) {
        List<Student> students = repo.findByCourseIgnoreCaseOrderByMarksDesc(course);
        if (students.isEmpty()) {
            throw new RuntimeException("No students found for course " + course);
        }
        return students;
    }

    @GetMapping("/topper")
    public Student getTopper() {
        return repo.findTopByOrderByMarksDesc()
                .orElseThrow(() -> new RuntimeException("No students available"));
    }

    @GetMapping("/stats")
    public StudentStatsResponse getStudentStats() {
        List<Student> students = repo.findAll();
        if (students.isEmpty()) {
            throw new RuntimeException("No students available");
        }

        DoubleSummaryStatistics statistics = students.stream()
                .mapToDouble(Student::getMarks)
                .summaryStatistics();

        return new StudentStatsResponse(
                students.size(),
                statistics.getAverage(),
                (int) statistics.getMax(),
                (int) statistics.getMin()
        );
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @Valid @RequestBody Student updatedStudent) {
        return repo.findById(id).map(student -> {
            student.setName(updatedStudent.getName());
            student.setCourse(updatedStudent.getCourse());
            student.setMarks(updatedStudent.getMarks());
            return repo.save(student);
        }).orElseThrow(() -> new RuntimeException("Student not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteStudent(@PathVariable int id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Student not found with id " + id);
        }
        repo.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Student deleted with id " + id));
    }

    public record MessageResponse(String message) {
    }

    public record StudentStatsResponse(int totalStudents, double averageMarks, int highestMarks, int lowestMarks) {
    }
}
