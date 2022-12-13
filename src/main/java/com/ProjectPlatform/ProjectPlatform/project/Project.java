package com.ProjectPlatform.ProjectPlatform.project;

import com.ProjectPlatform.ProjectPlatform.lecture.Lecture;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "projectName")
    private String projectName;
    @Column(name = "description", columnDefinition = "TEXT")
    private String  description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "projects_id", referencedColumnName = "id")
    private Collection<Result> results;

    public Project() {
    }

    public Project(String projectName, String description) {
        this.projectName = projectName;
        this.description = description;
    }
}
