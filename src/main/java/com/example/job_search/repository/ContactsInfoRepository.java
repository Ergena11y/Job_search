package com.example.job_search.repository;

import com.example.job_search.model.ContactsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactsInfoRepository extends JpaRepository<ContactsInfo, Integer> {
    List<ContactsInfo> findByResumeId(int resumeId);
    void deleteByResumeId(int resumeId);
}