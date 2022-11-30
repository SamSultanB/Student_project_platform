package com.ProjectPlatform.ProjectPlatform.student;

import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByEmail(String email);

}
