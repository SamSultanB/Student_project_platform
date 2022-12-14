package com.ProjectPlatform.ProjectPlatform.project;

import com.ProjectPlatform.ProjectPlatform.user.User;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private Project project;

    public Result() {
    }

    public Result(String doneProject, String mark, String comment) {
        this.doneProject = doneProject;
        this.mark = mark;
        this.commment = comment;
    }
}
