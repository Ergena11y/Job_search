package com.example.job_search.dao;

import com.example.job_search.model.Vacancies;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VacanciesDao {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Vacancies> getAllVacancies() {
        String sql = "SELECT * FROM vacancies WHERE is_active = TRUE";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancies.class));
    }
}
