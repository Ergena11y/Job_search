package com.example.job_search.service.impl;

import com.example.job_search.dto.EducationDto;
import com.example.job_search.dto.ResumeDto;
import com.example.job_search.dto.WorkExperienceDto;
import com.example.job_search.model.*;
import com.example.job_search.repository.*;
import com.example.job_search.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
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
    private final ContactsInfoRepository contactsInfoRepository;
    private final ContactTypesRepository contactTypesRepository;

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
        r.setSalary(resumeDto.getSalary() != null ? resumeDto.getSalary() : 0f);
        r.setIsActive(resumeDto.getIsActive() != null ? resumeDto.getIsActive() : true);
        r.setDescription(resumeDto.getDescription());
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

        Resumes saved = resumeRepository.save(r);

        saveContacts(saved, resumeDto);
        saveWorkExperience(saved, workExperience);
        saveEducation(saved, education);

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
        existing.setSalary(resumeDto.getSalary() != null ? resumeDto.getSalary() : 0f);
        existing.setIsActive(resumeDto.getIsActive() != null ? resumeDto.getIsActive() : existing.getIsActive());
        existing.setDescription(resumeDto.getDescription());
        existing.setUpdateTime(LocalDateTime.now());


        if (resumeDto.getCategoryId() != null) {
            categoryRepository.findById(resumeDto.getCategoryId())
                    .ifPresent(existing::setCategory);
        } else {
            existing.setCategory(null);
        }

        resumeRepository.save(existing);

        contactsInfoRepository.deleteByResumeId(id);
        saveContacts(existing, resumeDto);

        if (workExperience != null) {
            workExperienceRepository.deleteByResumeId(id);
            saveWorkExperience(existing, workExperience);
        }

        if (education != null) {
            educationRepository.deleteByResumeId(id);
            saveEducation(existing, education);
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
        return resumeRepository.findByCategoryIdAndIsActiveTrue(categoryId, pageable)
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

    private void saveContacts(Resumes resume, ResumeDto dto) {
        saveContact(resume, "phone",    dto.getContactPhone());
        saveContact(resume, "email",    dto.getContactEmail());
        saveContact(resume, "telegram", dto.getTelegram());
        saveContact(resume, "facebook", dto.getFacebook());
        saveContact(resume, "linkedin", dto.getLinkedin());
    }

    private void saveContact(Resumes resume, String typeName, String value) {
        if (value == null || value.isBlank()) return;

        ContactTypes type = contactTypesRepository.findByType(typeName)
                .orElseGet(() -> {
                    ContactTypes newType = new ContactTypes();
                    newType.setType(typeName);
                    return contactTypesRepository.save(newType);
                });

        ContactsInfo contact = new ContactsInfo();
        contact.setResume(resume);
        contact.setType(type);
        contact.setValue(value);
        contactsInfoRepository.save(contact);
    }

    private void saveWorkExperience(Resumes resume, List<WorkExperienceDto> list) {
        if (list == null) return;
        for (WorkExperienceDto w : list) {
            if (w.getCompanyName() != null && !w.getCompanyName().isBlank()) {
                if (w.getYears() != null && w.getYears() < 0) {
                    w.setYears(0);
                }
                WorkExperienceInfo we = new WorkExperienceInfo();
                we.setResume(resume);
                we.setYears(w.getYears());
                we.setCompanyName(w.getCompanyName());
                we.setPosition(w.getPosition());
                we.setResponsibilities(w.getResponsibilities());
                workExperienceRepository.save(we);
            }
        }
    }

    private void saveEducation(Resumes resume, List<EducationDto> list) {
        if (list == null) return;
        for (EducationDto e : list) {
            if (e.getInstitution() != null && !e.getInstitution().isBlank()) {
                EducationInfo educationInfo = new EducationInfo();
                educationInfo.setResume(resume);
                educationInfo.setInstitution(e.getInstitution());
                educationInfo.setProgram(e.getProgram());
                educationInfo.setStartDate(e.getStartDate());
                educationInfo.setEndDate(e.getEndDate());
                educationInfo.setDegree(e.getDegree());
                educationRepository.save(educationInfo);
            }
        }
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
                .categoryId(resume.getCategory() != null ? resume.getCategory().getId() : null)
                .categoryName(resume.getCategory() != null ? resume.getCategory().getName() : null)
                .applicantId(resume.getApplicant() != null ? (long) resume.getApplicant().getId() : null)
                .workExperience(workList)
                .education(eduList)
                .build();

        if (resume.getApplicant() != null) {
            dto.setApplicantName(resume.getApplicant().getName()
                    + " " + resume.getApplicant().getSurname());
            dto.setApplicantEmail(resume.getApplicant().getEmail());
            dto.setApplicantPhone(resume.getApplicant().getPhoneNumber());
        }

        List<ContactsInfo> contacts = contactsInfoRepository.findByResumeId(resume.getId());
        for (ContactsInfo c : contacts) {
            if (c.getType() == null) continue;
            switch (c.getType().getType()) {
                case "phone"    -> dto.setContactPhone(c.getValue());
                case "email"    -> dto.setContactEmail(c.getValue());
                case "telegram" -> dto.setTelegram(c.getValue());
                case "facebook" -> dto.setFacebook(c.getValue());
                case "linkedin" -> dto.setLinkedin(c.getValue());
            }
        }

        return dto;
    }
}