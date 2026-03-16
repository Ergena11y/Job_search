package com.example.job_search.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkExperienceInfo {
    private int id;
    private Resumes resumeId;
    private int years;
    private String companyName;
    private String position;
    private String responsibilities;
}
