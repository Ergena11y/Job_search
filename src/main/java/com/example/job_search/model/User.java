package com.example.job_search.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "users")


public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String avatar;

    @Column(name = "account_type", nullable = false)
    private String accountType;

    private boolean enabled ;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private List<Vacancies> vacancies;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "applicant")
    private List<Resumes> resumes;
}
