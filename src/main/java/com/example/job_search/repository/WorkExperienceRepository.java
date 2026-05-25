package com.example.job_search.repository;

import com.example.job_search.model.WorkExperienceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperienceInfo, Integer> {
    @Modifying(clearAutomatically = true)
    @Transactional
    void deleteByResumeId(int resumeId);
}
