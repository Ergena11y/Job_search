package com.example.job_search.dao;

import com.example.job_search.model.Vacancies;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VacanciesDao {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Vacancies> getAllVacancies() {
        String sql = "SELECT * FROM vacancies WHERE is_active = TRUE";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancies.class));
    }

    public Optional<Vacancies> getVacancyById(int id) {
        String sql = "SELECT * FROM vacancies WHERE id = ?";
        List<Vacancies> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancies.class), id);
        return Optional.ofNullable(DataAccessUtils.singleResult(list));
    }

    public List<Vacancies> getVacanciesByCategory(int categoryId){
        String sql = "SELECT * FROM vacancies WHERE category_id = ? AND is_active = TRUE";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancies.class), categoryId);
    }
    
    public List<Vacancies> getVacanciesRespondedByUser(int applicantId) {
        String sql = "SELECT * FROM vacancies v JOIN responded_aplicants ra ON ra.vacancy_id=v.id JOIN resumes r ON r.id = ra.resume_id WHERE r.applicant_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancies.class), applicantId);
    }
}
