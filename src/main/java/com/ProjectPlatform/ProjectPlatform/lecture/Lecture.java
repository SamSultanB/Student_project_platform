package com.ProjectPlatform.ProjectPlatform.lecture;

import com.ProjectPlatform.ProjectPlatform.project.Project;
import com.ProjectPlatform.ProjectPlatform.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "lectures")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "lectureName")
    private String lectureName;
    @Column(name = "password")
    private String password;

    @ManyToMany(mappedBy = "lectures")
    private Collection<User> users;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lecture_id", referencedColumnName = "id")
    private Collection<Project> projects;

    public Lecture(){

    }

    public Lecture(
            String lectureName,
            String password
    ) {
        this.lectureName = lectureName;
        this.password = password;
    }

}
