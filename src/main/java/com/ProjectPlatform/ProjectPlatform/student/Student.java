package com.ProjectPlatform.ProjectPlatform.student;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "students", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "studentName")
    private String userName;
    @Column(name = "StudentSurname")
    private String userSurname;

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "students_role",
            joinColumns = @JoinColumn(
                    name = "students_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))

    private Collection<StudentRole> roles;

    public Student(
            String userName,
            String userSurname,
            String email,
            String password,
            Collection<StudentRole> roles
    ) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

}
