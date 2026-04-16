package com.example.job_search.repository;

import com.example.job_search.model.Resumes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resumes, Integer> {
}
