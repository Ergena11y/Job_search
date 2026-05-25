package com.example.job_search.repository;

import com.example.job_search.model.EducationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EducationRepository extends JpaRepository<EducationInfo, Integer> {

    @Modifying(clearAutomatically = true)
    @Transactional
    void deleteByResumeId(int resumeId);
}
