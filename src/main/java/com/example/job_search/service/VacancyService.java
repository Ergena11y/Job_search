package com.example.job_search.service;

import com.example.job_search.dto.VacanciesDto;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VacancyService {

    Page<VacanciesDto> getAllVacancies(int page, int size, String sortBy);

    Page<VacanciesDto> getByCategory(int categoryId, int page, int size);

    List<VacanciesDto> getRespondedByUser(int applicantId);

    Page<VacanciesDto> getByAuthor(int authorId, int page, int size);

    void createVacancy(VacanciesDto vacanciesDto);

    void updateVacancy(int id, VacanciesDto vacanciesDto);

    void deleteVacancy(int id);
}
