package com.example.job_search.repository;

import com.example.job_search.model.Resumes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resumes, Integer> {
    
    Page<Resumes> findByIsActiveTrue(Pageable pageable);

    Page<Resumes> findByApplicantId(Integer applicantId, Pageable pageable);

    Page<Resumes> findByApplicantIdAndIsActiveTrue(Integer applicantId,  Pageable pageable);

    Page<Resumes> findByCategoryId(Integer categoryId, Pageable pageable);

    Page<Resumes> findByCategoryIdAndIsActiveTrue(Integer categoryId, Pageable pageable);
}
