package com.example.job_search.dao;


import com.example.job_search.model.Resumes;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public Optional<Resumes> getResumeById(int id) {
        String sql = "SELECT * FROM resumes WHERE id = ?";
        List<Resumes> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resumes.class), id);

        return Optional.ofNullable(DataAccessUtils.singleResult(list));
    }


    public void createResume(Resumes resume) {
        String sql = " INSERT INTO resumes (name, salary, is_active, created_date, update_time, category_id, applicant_id)\n" +
                "                VALUES (:name, :salary, :isActive, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, :categoryId, :applicantId)";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", resume.getName())
                .addValue("salary", resume.getSalary())
                .addValue("isActive", resume.getIsActive())
                .addValue("categoryId", resume.getCategoryId() != null ? resume.getCategoryId() : null)
                .addValue("applicantId", resume.getApplicantId() != null ? resume.getApplicantId() : null);
        namedParameterJdbcTemplate.update(sql, params);
    }

    public void updateResume(int id, Resumes resume) {
        String sql = "UPDATE resumes\n" +
                "                SET name = :name, salary = :salary, is_active = :isActive,\n" +
                "                    update_time = CURRENT_TIMESTAMP, category_id = :categoryId\n" +
                "                WHERE id = :id ";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", resume.getName())
                .addValue("salary", resume.getSalary())
                .addValue("isActive", resume.getIsActive())
                .addValue("categoryId", resume.getCategoryId() != null ? resume.getCategoryId() : null);
        namedParameterJdbcTemplate.update(sql, params);
    }

    public void deleteResumes(int id) {
        String sql = "DELETE FROM resumes WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
