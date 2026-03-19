package com.example.job_search.dao;


import com.example.job_search.model.Resumes;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Resumes> getAllResumes() {
        String sql = "SELECT * FROM resumes WHERE is_active = TRUE";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resumes.class));
    }

    public List<Resumes> getResumesByCategory(int categoryId) {
        String sql = "SELECT * FROM resumes WHERE category_id = ? AND is_active = TRUE";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resumes.class), categoryId);
    }

    public List<Resumes> getResumesByApplicant(int applicantId) {
        String sql = "SELECT * FROM resumes WHERE applicant_id = ? AND is_active = TRUE";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resumes.class), applicantId);
    }
}
