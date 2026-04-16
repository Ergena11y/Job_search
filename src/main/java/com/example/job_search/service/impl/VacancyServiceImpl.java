package com.example.job_search.service.impl;


import com.example.job_search.dao.VacanciesDao;
import com.example.job_search.dto.VacanciesDto;
import com.example.job_search.model.Vacancies;
import com.example.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacanciesDao vacanciesDao;


    @Override
    public List<VacanciesDto> getAllVacancies() {
        return vacanciesDao.getAllVacancies().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createVacancy(VacanciesDto dto) {
        log.info("Создание новой вакансии: {}", dto.getName());
        vacanciesDao.createVacancy(mapToModel(dto));
        log.info("Вакансия '{}' успешно создана", dto.getName());
    }

    @Override
    public void updateVacancy(int id, VacanciesDto dto) {
        log.info("Обновление вакансии с id: {}", id);
        vacanciesDao.updateVacancy(id, mapToModel(dto));
        log.info("Вакансия с id {} успешно обновлена", id);
    }

    @Override
    public void deleteVacancy(int id) {
        log.warn("Удаление вакансии с id: {}", id);
        vacanciesDao.deleteVacancy(id);
        log.info("Вакансия с id {} удалена", id);
    }

    @Override
    public List<VacanciesDto> getByCategory(int categoryId) {
        log.debug("Получение вакансий по категории id: {}", categoryId);
        return vacanciesDao.getVacanciesByCategory(categoryId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VacanciesDto> getRespondedByUser(int applicantId) {
        log.debug("Получение вакансий на которые откликнулся соискатель id: {}", applicantId);
        return vacanciesDao.getVacanciesRespondedByUser(applicantId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public VacanciesDto getById(int id) {
        return vacanciesDao.getVacancyById(id)
                .map(this::mapToDto)
                .orElseThrow();
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
                .categoryId(v.getCategoryId() != null ? v.getCategoryId() : null)
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
