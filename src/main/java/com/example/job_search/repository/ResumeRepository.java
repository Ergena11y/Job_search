package com.example.job_search.repository;

import com.example.job_search.model.Resumes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resumes, Integer> {
}
