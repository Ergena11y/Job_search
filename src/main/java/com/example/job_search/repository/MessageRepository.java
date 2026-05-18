package com.example.job_search.repository;

import com.example.job_search.model.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Messages, Integer> {
    List<Messages> findByRespondedApplicantsIdOrderByTimeStampAsc(Integer respondedApplicantId);
}
