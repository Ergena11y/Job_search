package com.example.job_search.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "work_experience_info")
public class WorkExperienceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id", nullable = false)
    private Resumes resume;

    private Integer years;


    @Column(name = "company_name")
    private String companyName;

    private String position;

    @Column(columnDefinition = "TEXT")
    private String responsibilities;
}
