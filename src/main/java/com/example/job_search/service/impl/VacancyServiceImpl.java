package com.example.job_search.service.impl;


import com.example.job_search.dao.VacanciesDao;
import com.example.job_search.model.Vacancies;
import com.example.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacanciesDao vacanciesDao;


    @Override
    public List<Vacancies> getAllVacancies() {
        return vacanciesDao.getAllVacancies();
    }

    @Override
    public void createVacancy(Vacancies vacancy) {
        vacanciesDao.createVacancy(vacancy);
    }

    @Override
    public void updateVacancy(int id, Vacancies vacancy) {
        vacanciesDao.updateVacancy(id, vacancy);
    }

    @Override
    public void deleteVacancy(int id) {
        vacanciesDao.deleteVacancy(id);
    }

    @Override
    public List<Vacancies> getByCategory(int categoryId) {
        return vacanciesDao.getVacanciesByCategory(categoryId);
    }

    @Override
    public List<Vacancies> getRespondedByUser(int applicantId) {
        return vacanciesDao.getVacanciesRespondedByUser(applicantId);
    }
}
