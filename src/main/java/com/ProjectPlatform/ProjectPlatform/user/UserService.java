package com.ProjectPlatform.ProjectPlatform.user;

import com.ProjectPlatform.ProjectPlatform.lecture.Lecture;
import com.ProjectPlatform.ProjectPlatform.web.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;


public interface UserService extends UserDetailsService {

    User save(UserDto userDto);

    User findByEmail(String email);

    User findUserById(Long id);

    List<User> findAll();

}
