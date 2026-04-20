package com.example.job_search.service;

import com.example.job_search.dto.ResumeDto;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ResumeService {

    Page<ResumeDto> getAllResumes(int page, int size);

    void createResumes(ResumeDto resumeDto);

    void updateResumes(int id, ResumeDto resumeDto);

    void deleteResumes(int id);

    Page<ResumeDto> getByCategory(int categoryId, int page, int size);

    Page<ResumeDto> getByApplicant(int applicantId, int page, int size);

}
