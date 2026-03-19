package com.example.job_search.service.impl;


import com.example.job_search.dao.ResumeDao;
import com.example.job_search.model.Resumes;
import com.example.job_search.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor

public class ResumeServiceImpl implements ResumeService {

    private final ResumeDao resumeDao;

    @Override
    public List<Resumes> getAllResumes() {
        return resumeDao.getAllResumes();
    }

    @Override
    public void createResumes(Resumes resume) {
        resumeDao.createResume(resume);
    }

    @Override
    public void updateResumes(int id, Resumes resume) {
        resumeDao.updateResume(id, resume);
    }

    @Override
    public void deleteResumes(int id) {
        resumeDao.deleteResumes(id);
    }

    @Override
    public List<Resumes> getByCategory(int categoryId) {
        return resumeDao.getResumesByCategory(categoryId);
    }

    @Override
    public List<Resumes> getByApplicant(int applicantId) {
        return resumeDao.getResumesByApplicant(applicantId);
    }
}
