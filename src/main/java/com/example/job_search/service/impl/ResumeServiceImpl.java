package com.example.job_search.service.impl;


import com.example.job_search.dto.ResumeDto;
import com.example.job_search.model.Resumes;
import com.example.job_search.repository.ResumeRepository;
import com.example.job_search.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor

public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    @Override
    public Page<ResumeDto> getAllResumes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updateTime"));


        return resumeRepository.findByIsActiveTrue(pageable)
                .map(this::mapToDto);
    }

    @Override
    public void createResumes(ResumeDto resumeDto) {
        log.info("Создание нового резюме: {}", resumeDto.getName());
        Resumes r = mapToModel(resumeDto);
        r.setCreatedDate(LocalDateTime.now());
        r.setUpdateTime(LocalDateTime.now());
        resumeRepository.save(r);
        log.info("Резюме '{}' успешно создано", resumeDto.getName());
    }

    @Override
    public void updateResumes(int id, ResumeDto resumeDto) {
        log.info("Обновление резюме с id: {}", id);
        Resumes existing = resumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resume not found: " + id));
        existing.setName(resumeDto.getName());
        existing.setSalary(resumeDto.getSalary());
        existing.setIsActive(resumeDto.getIsActive());
        existing.setCategory(resumeDto.getCategoryId());
        existing.setUpdateTime(LocalDateTime.now());
        resumeRepository.save(existing);
        log.info("Резюме с id {} успешно обновлено", id);
    }

    @Override
    public void deleteResumes(int id) {
        log.warn("Удаление резюме с id: {}", id);
        resumeRepository.deleteById(id);
        log.info("Резюме с id {} удалено", id);
    }

    @Override
    public Page<ResumeDto> getByCategory(int categoryId, int page, int size) {
        log.debug("Получение резюме по категории id: {}", categoryId);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updateTime"));

        return resumeRepository.findByCategoryIdAndIsActiveTrue(categoryId,  pageable)
                .map(this::mapToDto);
    }

    @Override
    public Page<ResumeDto> getByApplicant(int applicantId, int page, int size) {
        log.debug("Получение резюме соискателя id: {}", applicantId);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updateTime"));

        return resumeRepository.findByApplicantId(applicantId, pageable)
                .map(this::mapToDto);
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
