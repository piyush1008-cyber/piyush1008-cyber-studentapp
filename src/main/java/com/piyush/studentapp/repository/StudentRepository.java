package com.piyush.studentapp.repository;

import com.piyush.studentapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAllByOrderByMarksDesc();

    List<Student> findByCourseIgnoreCaseOrderByMarksDesc(String course);

    Optional<Student> findTopByOrderByMarksDesc();
}
