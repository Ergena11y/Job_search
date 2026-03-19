package com.example.job_search.service;

import com.example.job_search.model.RespondedApplicants;

import java.util.List;

public interface RespondedApplicantsService {
    List<RespondedApplicants> getAll();
    List<RespondedApplicants> getByVacancyId(int vacancyId);
    void  respond(RespondedApplicants responded);
}
