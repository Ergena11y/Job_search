package com.example.job_search.service.impl;


import com.example.job_search.dao.ResumeDao;
import com.example.job_search.dto.ResumeDto;
import com.example.job_search.model.Resumes;
import com.example.job_search.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
        log.info("Создание нового резюме: {}", resumeDto.getName());
        resumeDao.createResume(mapToModel(resumeDto));
        log.info("Резюме '{}' успешно создано", resumeDto.getName());
    }

    @Override
    public void updateResumes(int id, ResumeDto resumeDto) {
        log.info("Обновление резюме с id: {}", id);
        resumeDao.updateResume(id, mapToModel(resumeDto));
        log.info("Резюме с id {} успешно обновлено", id);
    }

    @Override
    public void deleteResumes(int id) {
        log.warn("Удаление резюме с id: {}", id);
        resumeDao.deleteResumes(id);
        log.info("Резюме с id {} удалено", id);
    }

    @Override
    public List<ResumeDto> getByCategory(int categoryId) {
        log.debug("Получение резюме по категории id: {}", categoryId);
        return resumeDao.getResumesByCategory(categoryId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResumeDto> getByApplicant(int applicantId) {
        log.debug("Получение резюме соискателя id: {}", applicantId);
        return resumeDao.getResumesByApplicant(applicantId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResumeDto getById(int id) {
        return resumeDao.getResumeById(id)
                .map(this::mapToDto)
                .orElseThrow();
    }

    private ResumeDto mapToDto(Resumes resume) {
        return ResumeDto.builder()
                .name(resume.getName())
                .salary(resume.getSalary())
                .isActive(resume.getIsActive())
                .createdDate(resume.getCreatedDate())
                .updateTime(resume.getUpdateTime())
                .categoryId(resume.getCategory() != null ? resume.getCategory().getId() : null)
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
