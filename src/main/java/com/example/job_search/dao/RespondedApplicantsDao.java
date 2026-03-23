package com.example.job_search.dao;


import com.example.job_search.model.RespondedApplicants;
import com.example.job_search.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RespondedApplicantsDao {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<RespondedApplicants>  getAll(){
        String sql = "SELECT * FROM responded_applicants";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RespondedApplicants.class));
    }

    public List<RespondedApplicants> getByVacancyId(int vacancyId ){
        String sql = "SELECT * FROM responded_applicants WHERE vacancy_id = ? ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RespondedApplicants.class), vacancyId);
    }

    public void respond(RespondedApplicants responded){
        String sql = "INSERT INTO responded_applicants (resume_id, vacancy_id, confirmation) VALUES (:resumeId, :vacancyId, :confirmation)";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("resumeId", responded.getResumeId() != null ? responded.getResumeId() : null)
                .addValue("vacancyId", responded.getVacancyId() != null ? responded.getVacancyId(): null)
                .addValue("confirmation", responded.isConfirmation());
        namedParameterJdbcTemplate.update(sql, params);
    }

    public List<User> getUsersByVacancyId(int vacancyId) {
        String sql = "SELECT u.* FROM users u " +
                "JOIN resumes r ON u.id = r.applicant_id " +
                "JOIN responded_applicants ra ON r.id = ra.resume_id " +
                "WHERE ra.vacancy_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), vacancyId);
    }
}
