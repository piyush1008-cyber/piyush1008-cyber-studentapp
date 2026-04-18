package com.piyush.studentapp.config;

import com.piyush.studentapp.model.AppUser;
import com.piyush.studentapp.model.Student;
import com.piyush.studentapp.repository.StudentRepository;
import com.piyush.studentapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DemoDataLoader {

    @Bean
    CommandLineRunner loadDemoData(StudentRepository studentRepository,
                                   UserRepository userRepository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                AppUser admin = new AppUser();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("1234"));
                admin.setRole("ADMIN");
                userRepository.save(admin);
            }

            if (studentRepository.count() == 0) {
                studentRepository.save(createStudent("Aarav Sharma", "Computer Science", 91));
                studentRepository.save(createStudent("Sneha Patel", "Electronics", 87));
                studentRepository.save(createStudent("Rohit Verma", "Mechanical", 83));
            }
        };
    }

    private Student createStudent(String name, String course, int marks) {
        Student student = new Student();
        student.setName(name);
        student.setCourse(course);
        student.setMarks(marks);
        return student;
    }
}
