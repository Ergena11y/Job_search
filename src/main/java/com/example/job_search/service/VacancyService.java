package com.example.job_search.service;

import com.example.job_search.dto.VacanciesDto;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface VacancyService {
    List<VacanciesDto> getAllVacancies();
    void createVacancy(VacanciesDto vacanciesDto);
    void updateVacancy(int id, VacanciesDto vacanciesDto);
    void deleteVacancy(int id);
    List<VacanciesDto> getByCategory(int categoryId);
    List<VacanciesDto> getRespondedByUser(int applicantId);

    VacanciesDto getById(int id);
}
