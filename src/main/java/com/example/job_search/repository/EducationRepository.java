package com.example.job_search.repository;

import com.example.job_search.model.EducationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<EducationInfo, Integer> {
    void deleteByResumeId(int resumeId);
}
