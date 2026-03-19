package com.example.job_search.service;

import com.example.job_search.model.Resumes;

import java.util.List;

public interface ResumeService {
    List<Resumes> getAllResumes();
    void createResumes(Resumes  resume);
    void updateResumes(int id, Resumes resume);
    void deleteResumes(int id);
    List<Resumes> getByCategory(int categoryId);
    List<Resumes> getByApplicant(int applicantId);
}
