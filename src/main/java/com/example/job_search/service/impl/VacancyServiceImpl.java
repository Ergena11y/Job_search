package com.example.job_search.service.impl;


import com.example.job_search.dto.VacanciesDto;
import com.example.job_search.model.Vacancies;
import com.example.job_search.repository.VacancyRepository;
import com.example.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository vacancyRepository;


    @Override
    public Page<VacanciesDto> getAllVacancies(int page, int size, String sortBy) {
        if ("responses".equalsIgnoreCase(sortBy)) {
            Pageable pageable = PageRequest.of(page, size);
            return vacancyRepository.findAllActiveOrderByResponseCount(pageable)
                    .map(this::mapToDto);
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updateTime"));

        return vacancyRepository.findByIsActiveTrue(pageable).map(this::mapToDto);
    }

    @Override
    public Page<VacanciesDto> getByCategory(int categoryId, int page, int size) {
        log.debug("Получение вакансий по категории id: {}", categoryId);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updateTime"));

        return vacancyRepository.findByCategoryIdAndIsActiveTrue(categoryId, pageable).map(this::mapToDto);
    }

    @Override
    public void createVacancy(VacanciesDto dto) {
        log.info("Создание новой вакансии: {}", dto.getName());
        Vacancies v = mapToModel(dto);
        v.setCreatedDate(LocalDateTime.now());
        v.setUpdateTime(LocalDateTime.now());
        vacancyRepository.save(v);
        log.info("Вакансия '{}' успешно создана", dto.getName());
    }

    @Override
    public void updateVacancy(int id, VacanciesDto dto) {
        log.info("Обновление вакансии с id: {}", id);
       Vacancies existing = vacancyRepository.findById(id)
                       .orElseThrow(RuntimeException::new);
       existing.setName(dto.getName());
       existing.setDescription(dto.getDescription());
       existing.setSalary(dto.getSalary());
       existing.setExpFrom(dto.getExpFrom());
       existing.setExpTo(dto.getExpTo());
       existing.setIsActive(dto.getIsActive());
       existing.setCategory(dto.getCategoryId());
       existing.setUpdateTime(dto.getUpdateTime());
       vacancyRepository.save(existing);
        log.info("Вакансия с id {} успешно обновлена", id);
    }

    @Override
    public void deleteVacancy(int id) {
        log.warn("Удаление вакансии с id: {}", id);
        vacancyRepository.deleteById(id);
        log.info("Вакансия с id {} удалена", id);
    }


    @Override
    public List<VacanciesDto> getRespondedByUser(int applicantId) {
        log.debug("Получение вакансий на которые откликнулся соискатель id: {}", applicantId);

        return vacancyRepository.findRespondedByApplicant(applicantId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<VacanciesDto> getByAuthor(int authorId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updateTime"));

        return vacancyRepository.findByAuthorId(authorId, pageable)
                .map(this::mapToDto);
    }


    private VacanciesDto mapToDto(Vacancies v) {
        return VacanciesDto.builder()
                .name(v.getName())
                .description(v.getDescription())
                .salary(v.getSalary())
                .expFrom(v.getExpFrom())
                .expTo(v.getExpTo())
                .isActive(v.getIsActive())
                .createdDate(v.getCreatedDate())
                .updateTime(v.getUpdateTime())
                .categoryId(v.getCategory() != null ? v.getCategory().getId() : null)
                .authorId(v.getAuthor() != null ? v.getAuthor().getId() : null)
                .build();
    }

    private Vacancies mapToModel(VacanciesDto dto) {
        Vacancies v = new Vacancies();
        v.setName(dto.getName());
        v.setDescription(dto.getDescription());
        v.setSalary(dto.getSalary());
        v.setExpFrom(dto.getExpFrom());
        v.setExpTo(dto.getExpTo());
        v.setIsActive(dto.getIsActive());
        return v;
    }

}
