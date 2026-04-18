
package com.piyush.studentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.piyush.studentapp.repository")
public class StudentappApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentappApplication.class, args);
    }
}
