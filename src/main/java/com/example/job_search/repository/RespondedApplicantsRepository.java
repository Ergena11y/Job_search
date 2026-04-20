package com.example.job_search.repository;

import com.example.job_search.model.RespondedApplicants;
import com.example.job_search.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespondedApplicantsRepository extends JpaRepository<RespondedApplicants, Integer> {
    
    List<RespondedApplicants> findByVacancyId(Integer vacancyId);

    @Query("SELECT ra.resume.applicant FROM RespondedApplicants ra" +
            " WHERE ra.vacancy.id = :vacancyId")
    List<User> findUsersByVacancyId(@Param("vacancyId") Integer vacancyId);
}
