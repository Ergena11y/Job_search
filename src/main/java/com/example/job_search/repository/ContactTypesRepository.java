package com.example.job_search.repository;

import com.example.job_search.model.ContactTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactTypesRepository extends JpaRepository<ContactTypes, Integer> {
    Optional<ContactTypes> findByType(String type);
}