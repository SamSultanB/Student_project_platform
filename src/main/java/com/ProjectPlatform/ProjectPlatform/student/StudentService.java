package com.ProjectPlatform.ProjectPlatform.student;

import com.ProjectPlatform.ProjectPlatform.web.dto.StudentDto;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface StudentService extends UserDetailsService {

    Student save(StudentDto studentDto);
}
