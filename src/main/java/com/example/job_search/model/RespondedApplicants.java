package com.example.job_search.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "responded_applicants")
public class RespondedApplicants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resumes resume;

    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private Vacancies vacancy;

    private boolean confirmation;
}
