package com.ProjectPlatform.ProjectPlatform.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String role;

    public UserDto() {

    }

    public UserDto(String name, String surname, String email, String password, String role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
