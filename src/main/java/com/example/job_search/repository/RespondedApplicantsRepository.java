package com.example.job_search.repository;

import com.example.job_search.model.RespondedApplicants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespondedApplicantsRepository extends JpaRepository<RespondedApplicants, Integer> {
}
