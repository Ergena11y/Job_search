package com.example.job_search.dao;

import com.example.job_search.model.Vacancies;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
        String sql = "SELECT * FROM vacancies v JOIN responded_applicants ra ON ra.vacancy_id=v.id JOIN resumes r ON r.id = ra.resume_id WHERE r.applicant_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancies.class), applicantId);
    }

    public void createVacancy(Vacancies vacancy) {
        String sql = " INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to,\n" +
                "                                       is_active, author_id, created_date, update_time)\n" +
                "                VALUES (:name, :description, :categoryId, :salary, :expFrom, :expTo,\n" +
                "                        :isActive, :authorId, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", vacancy.getName())
                .addValue("description", vacancy.getDescription())
                .addValue("categoryId", vacancy.getCategoryId() != null ? vacancy.getCategoryId().getId() : null)
                .addValue("salary", vacancy.getSalary())
                .addValue("expFrom", vacancy.getExpFrom())
                .addValue("expTo", vacancy.getExpTo())
                .addValue("isActive", vacancy.isActive())
                .addValue("authorId", vacancy.getAuthorId() != null ? vacancy.getAuthorId().getId() : null);
        namedParameterJdbcTemplate.update(sql, params);
    }

    public void updateVacancy(int id, Vacancies vacancy) {
        String sql = " UPDATE vacancies\n" +
                "                SET name = :name, description = :description, category_id = :categoryId,\n" +
                "                    salary = :salary, exp_from = :expFrom, exp_to = :expTo,\n" +
                "                    is_active = :isActive, update_time = CURRENT_TIMESTAMP\n" +
                "                WHERE id = :id";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", vacancy.getName())
                .addValue("description", vacancy.getDescription())
                .addValue("categoryId", vacancy.getCategoryId() != null ? vacancy.getCategoryId().getId() : null)
                .addValue("salary", vacancy.getSalary())
                .addValue("expFrom", vacancy.getExpFrom())
                .addValue("expTo", vacancy.getExpTo())
                .addValue("isActive", vacancy.isActive());
        namedParameterJdbcTemplate.update(sql, params);
    }

    public void deleteVacancy(int id) {
        String sql = "DELETE FROM vacancies WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
