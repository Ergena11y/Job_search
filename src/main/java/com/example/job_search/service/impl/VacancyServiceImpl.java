package com.example.job_search.service.impl;


import com.example.job_search.dao.VacanciesDao;
import com.example.job_search.dto.VacanciesDto;
import com.example.job_search.model.Vacancies;
import com.example.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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
        vacanciesDao.createVacancy(mapToModel(dto));
    }

    @Override
    public void updateVacancy(int id, VacanciesDto dto) {
        vacanciesDao.updateVacancy(id, mapToModel(dto));
    }

    @Override
    public void deleteVacancy(int id) {
        vacanciesDao.deleteVacancy(id);
    }

    @Override
    public List<VacanciesDto> getByCategory(int categoryId) {
        return vacanciesDao.getVacanciesByCategory(categoryId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VacanciesDto> getRespondedByUser(int applicantId) {
        return vacanciesDao.getVacanciesRespondedByUser(applicantId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
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
                .authorId(v.getAuthorId() != null ? v.getAuthorId() : null)
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
