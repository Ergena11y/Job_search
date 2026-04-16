package com.example.job_search.repository;

import com.example.job_search.model.Vacancies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancies, Integer> {
}
