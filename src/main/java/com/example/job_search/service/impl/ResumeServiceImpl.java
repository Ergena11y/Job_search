package com.example.job_search.service.impl;


import com.example.job_search.dao.ResumeDao;
import com.example.job_search.dto.ResumeDto;
import com.example.job_search.model.Resumes;
import com.example.job_search.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor

public class ResumeServiceImpl implements ResumeService {

    private final ResumeDao resumeDao;

    @Override
    public List<ResumeDto> getAllResumes() {
        return resumeDao.getAllResumes().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createResumes(ResumeDto resumeDto) {
        resumeDao.createResume(mapToModel(resumeDto));
    }

    @Override
    public void updateResumes(int id, ResumeDto resumeDto) {
        resumeDao.updateResume(id, mapToModel(resumeDto));
    }

    @Override
    public void deleteResumes(int id) {
        resumeDao.deleteResumes(id);
    }

    @Override
    public List<ResumeDto> getByCategory(int categoryId) {
        return resumeDao.getResumesByCategory(categoryId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResumeDto> getByApplicant(int applicantId) {
        return resumeDao.getResumesByApplicant(applicantId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private ResumeDto mapToDto(Resumes resume) {
        return ResumeDto.builder()
                .name(resume.getName())
                .salary(resume.getSalary())
                .isActive(resume.getIsActive())
                .createdDate(resume.getCreatedDate())
                .updateTime(resume.getUpdateTime())
                .categoryId(resume.getCategoryId() != null ? resume.getCategoryId() : null)
                .build();
    }

    private Resumes mapToModel(ResumeDto dto) {
        Resumes resume = new Resumes();
        resume.setName(dto.getName());
        resume.setSalary(dto.getSalary());
        resume.setIsActive(dto.getIsActive());
        return resume;
    }
}
