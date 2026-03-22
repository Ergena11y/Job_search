package com.example.job_search.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RespondedApplicants {
    private Integer id;
    private Resumes resumeId;
    private Vacancies vacancyId;
    private boolean confirmation;
}
