package com.ProjectPlatform.ProjectPlatform.student;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role")
public class StudentRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public StudentRole() {
    }

    public StudentRole(String name) {
        super();
        this.name = name;
    }
}
