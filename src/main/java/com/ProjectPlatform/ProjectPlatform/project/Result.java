package com.ProjectPlatform.ProjectPlatform.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "doneProject")
    private String doneProject;

    @Column(name = "mark")
    private String mark;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String commment;

    public Result() {
    }

    public Result(String doneProject, String mark, String comment) {
        this.doneProject = doneProject;
        this.mark = mark;
        this.commment = comment;
    }
}
