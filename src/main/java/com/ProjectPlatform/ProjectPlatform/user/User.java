package com.ProjectPlatform.ProjectPlatform.user;

import com.ProjectPlatform.ProjectPlatform.lecture.Lecture;
import com.ProjectPlatform.ProjectPlatform.project.Result;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "userName")
    private String userName;
    @Column(name = "userSurname")
    private String userSurname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "users_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))

    private Collection<Role> userRole;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_lectures",
            joinColumns = @JoinColumn(
                    name = "users_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "lectures_id", referencedColumnName = "id")
    )
    private Collection<Lecture> lectures;

    public User(
            String userName,
            String userSurname,
            String email,
            String password,
            Collection<Role>  userRole
    ) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    public void addLecture(Lecture lecture){
        this.lectures.add(lecture);
    }

    public void removeLecture(Long lectureId){
        Lecture lecture = this.lectures.stream().filter(lecture1 -> lecture1.getId() == lectureId).findFirst().orElse(null);
        if(lecture != null){
            this.lectures.remove(lecture);
            lecture.getUsers().remove(this);
        }
    }

}
