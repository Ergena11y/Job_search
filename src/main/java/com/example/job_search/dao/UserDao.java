package com.example.job_search.dao;

import com.example.job_search.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<User> getAllUsers(){
        String sql = "SELECT * FROM users";

        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<User> getUserById(int id){
        String sql = "SELECT * FROM users WHERE id = ?";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), id);
        return Optional.ofNullable(DataAccessUtils.singleResult(users));
    }

    public List<User> getUsersByName(String name) {
        String sql = "SELECT * FROM users WHERE name = :name OR surname = :name";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", name);
        return namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<User> getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = :email";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("email", email);
        List<User> users = namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(User.class));
        return Optional.ofNullable(DataAccessUtils.singleResult(users));
    }

    public Optional<User> getUserByPhoneNumber(String phoneNumber) {
        String sql = "SELECT * FROM users WHERE phone_number = :phone";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("phone", phoneNumber);
        List<User> users = namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(User.class));
        return Optional.ofNullable(DataAccessUtils.singleResult(users));
    }
}
