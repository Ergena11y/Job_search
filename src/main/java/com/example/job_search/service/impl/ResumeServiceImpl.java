package com.example.job_search.service.impl;


import com.example.job_search.dto.ResumeDto;
import com.example.job_search.model.Category;
import com.example.job_search.model.Resumes;
import com.example.job_search.repository.CategoryRepository;
import com.example.job_search.repository.ResumeRepository;
import com.example.job_search.repository.UserRepository;
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
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public Page<ResumeDto> getAllResumes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updateTime"));


        return resumeRepository.findByIsActiveTrue(pageable)
                .map(this::mapToDto);
    }

    @Override
    public void createResumes(ResumeDto resumeDto) {
        log.info("Создание нового резюме: {}", resumeDto.getName());
        Resumes r = new Resumes();

        r.setName(resumeDto.getName());
        r.setSalary(resumeDto.getSalary() != null ? resumeDto.getSalary() : 0);
        r.setIsActive(resumeDto.getIsActive() != null ? resumeDto.getIsActive() : true);
        r.setCreatedDate(LocalDateTime.now());
        r.setUpdateTime(LocalDateTime.now());

        if (resumeDto.getCategoryId() != null) {
            categoryRepository.findById(resumeDto.getCategoryId())
                    .ifPresent(r::setCategory);
        }

        if (resumeDto.getApplicantId() != null) {
            userRepository.findById(resumeDto.getApplicantId().intValue())
                    .ifPresent(r::setApplicant);
        }

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

        if (resumeDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(resumeDto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Категория не найдена"));
            existing.setCategory(category);
        }

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

    @Override
    public ResumeDto getById(int id) {
        return resumeRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Resume not found"));
    }

    private ResumeDto mapToDto(Resumes resume) {
        return ResumeDto.builder()
                .id((long) resume.getId())
                .name(resume.getName())
                .salary(resume.getSalary())
                .isActive(resume.getIsActive())
                .createdDate(resume.getCreatedDate())
                .updateTime(resume.getUpdateTime())
                .categoryId(resume.getCategory() != null ? resume.getCategory().getId() : null)
                .applicantId(resume.getApplicant() != null ? (long) resume.getApplicant().getId() : null)
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
