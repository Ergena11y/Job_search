package com.example.job_search.service.impl;

import com.example.job_search.dto.EducationDto;
import com.example.job_search.dto.ResumeDto;
import com.example.job_search.dto.WorkExperienceDto;
import com.example.job_search.model.EducationInfo;
import com.example.job_search.model.Resumes;
import com.example.job_search.model.WorkExperienceInfo;
import com.example.job_search.repository.*;
import com.example.job_search.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final WorkExperienceRepository workExperienceRepository;
    private final EducationRepository educationRepository;

    @Override
    public Page<ResumeDto> getAllResumes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size,
                Sort.by(Sort.Direction.DESC, "updateTime"));
        return resumeRepository.findByIsActiveTrue(pageable).map(this::mapToDto);
    }

    @Override
    public void createResumes(ResumeDto resumeDto,
                              List<WorkExperienceDto> workExperience,
                              List<EducationDto> education) {
        log.info("Создание нового резюме: {}", resumeDto.getName());
        Resumes r = new Resumes();
        r.setName(resumeDto.getName());
        r.setSalary(resumeDto.getSalary() != null ? resumeDto.getSalary() : 0 );
        r.setIsActive(resumeDto.getIsActive() != null ? resumeDto.getIsActive() : true);
        r.setDescription(resumeDto.getDescription());
        r.setCreatedDate(LocalDateTime.now());
        r.setUpdateTime(LocalDateTime.now());

        if (resumeDto.getCategoryId() != null){
            categoryRepository.findById(resumeDto.getCategoryId())
                    .ifPresent(r::setCategory);
        }
        if (resumeDto.getApplicantId() != null){
            userRepository.findById(resumeDto.getApplicantId().intValue())
                    .ifPresent(r::setApplicant);
        }

        Resumes saved = resumeRepository.save(r);

        if(workExperience != null){
            for (WorkExperienceDto w : workExperience){
                if(w.getCompanyName() != null && !w.getCompanyName().isBlank()){
                    WorkExperienceInfo we = new WorkExperienceInfo();
                    we.setResume(saved);
                    we.setYears(w.getYears());
                    we.setCompanyName(w.getCompanyName());
                    we.setPosition(w.getPosition());
                    we.setResponsibilities(w.getResponsibilities());
                    workExperienceRepository.save(we);
                }
            }
        }

        if (education != null){
            for (EducationDto e : education){
                if (e.getInstitution() != null && !e.getInstitution().isBlank()){
                    EducationInfo educationInfo = new EducationInfo();
                    educationInfo.setResume(saved);
                    educationInfo.setInstitution(e.getInstitution());
                    educationInfo.setProgram(e.getProgram());
                    educationInfo.setStartDate(e.getStartDate());
                    educationInfo.setEndDate(e.getEndDate());
                    educationInfo.setDegree(e.getDegree());
                    educationRepository.save(educationInfo);
                }
            }
        }

        log.info("Резюме '{}' успешно создано", resumeDto.getName());
    }

    @Override
    @Transactional
    public void updateResumes(int id, ResumeDto resumeDto,
                              List<WorkExperienceDto> workExperience,
                              List<EducationDto> education) {
        log.info("Обновление резюме с id: {}", id);
        Resumes existing = resumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resume not found: " + id));

        existing.setName(resumeDto.getName());
        existing.setSalary(resumeDto.getSalary() != null ? resumeDto.getSalary() : 0);
        existing.setIsActive(resumeDto.getIsActive() != null ? resumeDto.getIsActive() : true);
        existing.setDescription(resumeDto.getDescription());
        existing.setUpdateTime(LocalDateTime.now());

        if (resumeDto.getCategoryId() != null) {
            categoryRepository.findById(resumeDto.getCategoryId())
                    .ifPresent(existing::setCategory);
        }

        resumeRepository.save(existing);

        if (workExperience != null) {
            workExperienceRepository.deleteByResumeId(id);
            for (WorkExperienceDto w : workExperience) {
                if (w.getCompanyName() != null && !w.getCompanyName().isBlank()) {
                    WorkExperienceInfo we = new WorkExperienceInfo();
                    we.setResume(existing);
                    we.setYears(w.getYears());
                    we.setCompanyName(w.getCompanyName());
                    we.setPosition(w.getPosition());
                    we.setResponsibilities(w.getResponsibilities());
                    workExperienceRepository.save(we);
                }
            }
        }

        if (education != null) {
            educationRepository.deleteByResumeId(id);
            for (EducationDto e : education) {
                if (e.getInstitution() != null && !e.getInstitution().isBlank()) {
                    EducationInfo educationInfo = new EducationInfo();
                    educationInfo.setResume(existing);
                    educationInfo.setInstitution(e.getInstitution());
                    educationInfo.setProgram(e.getProgram());
                    educationInfo.setStartDate(e.getStartDate());
                    educationInfo.setEndDate(e.getEndDate());
                    educationInfo.setDegree(e.getDegree());
                    educationRepository.save(educationInfo);
                }
            }
        }

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
        Pageable pageable = PageRequest.of(page, size,
                Sort.by(Sort.Direction.DESC, "updateTime"));
        return resumeRepository
                .findByCategoryIdAndIsActiveTrue(categoryId, pageable)
                .map(this::mapToDto);
    }

    @Override
    public Page<ResumeDto> getByApplicant(int applicantId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size,
                Sort.by(Sort.Direction.DESC, "updateTime"));
        return resumeRepository.findByApplicantId(applicantId, pageable).map(this::mapToDto);
    }

    @Override
    @Transactional
    public ResumeDto getById(int id) {
        return resumeRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Resume not found"));
    }

    private ResumeDto mapToDto(Resumes resume) {
        List<WorkExperienceDto> workList = resume.getWorkExperience() != null
                ? resume.getWorkExperience().stream()
                .map(w -> WorkExperienceDto.builder()
                        .years(w.getYears())
                        .companyName(w.getCompanyName())
                        .position(w.getPosition())
                        .responsibilities(w.getResponsibilities())
                        .build())
                .collect(Collectors.toList())
                : List.of();

        List<EducationDto> eduList = resume.getEducation() != null
                ? resume.getEducation().stream()
                .map(e -> EducationDto.builder()
                        .institution(e.getInstitution())
                        .program(e.getProgram())
                        .startDate(e.getStartDate())
                        .endDate(e.getEndDate())
                        .degree(e.getDegree())
                        .build())
                .collect(Collectors.toList())
                : List.of();

        ResumeDto dto = ResumeDto.builder()
                .id((long) resume.getId())
                .name(resume.getName())
                .salary(resume.getSalary())
                .description(resume.getDescription())
                .isActive(resume.getIsActive())
                .createdDate(resume.getCreatedDate())
                .updateTime(resume.getUpdateTime())
                .categoryId(resume.getCategory() != null
                        ? resume.getCategory().getId() : null)
                .categoryName(resume.getCategory() != null
                        ? resume.getCategory().getName() : null)
                .applicantId(resume.getApplicant() != null
                        ? (long) resume.getApplicant().getId() : null)
                .workExperience(workList)
                .education(eduList)
                .build();

        if (resume.getApplicant() != null) {
            dto.setApplicantName(resume.getApplicant().getName()
                    + " " + resume.getApplicant().getSurname());
            dto.setApplicantEmail(resume.getApplicant().getEmail());
            dto.setApplicantPhone(resume.getApplicant().getPhoneNumber());
        }

        return dto;
    }
}
