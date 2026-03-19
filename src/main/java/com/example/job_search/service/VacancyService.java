package com.example.job_search.service;

import com.example.job_search.model.Vacancies;

import java.util.List;

public interface VacancyService {
    List<Vacancies> getAllVacancies();
    void createVacancy(Vacancies vacancy);
    void updateVacancy(int id, Vacancies vacancy);
    void deleteVacancy(int id);
    List<Vacancies> getByCategory(int categoryId);
    List<Vacancies> getRespondedByUser(int applicantId);
}
