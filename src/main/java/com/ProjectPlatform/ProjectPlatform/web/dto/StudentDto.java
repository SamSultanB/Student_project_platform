package com.ProjectPlatform.ProjectPlatform.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {
    private String name;
    private String surname;
    private String email;
    private String password;

    public StudentDto() {
    }

    public StudentDto(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

}
