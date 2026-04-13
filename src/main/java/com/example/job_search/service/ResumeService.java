package com.example.job_search.service;

import com.example.job_search.dto.ResumeDto;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> getAllResumes();
    void createResumes(ResumeDto resumeDto);
    void updateResumes(int id, ResumeDto resumeDto);
    void deleteResumes(int id);
    List<ResumeDto> getByCategory(int categoryId);
    List<ResumeDto> getByApplicant(int applicantId);

    ResumeDto getById(int id);
}
