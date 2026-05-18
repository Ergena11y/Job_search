package com.example.job_search.repository;

import com.example.job_search.model.WorkExperienceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperienceInfo, Integer> {
    void deleteByResumeId(int resumeId);
}
