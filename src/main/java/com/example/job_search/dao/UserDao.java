package com.example.job_search.dao;

import com.example.job_search.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }


    public User createUser(User user) {
        String sql = "INSERT INTO users (name, surname, age, email, password, phone_number, avatar, account_type)\n" +
                "                VALUES (:name, :surname, :age, :email, :password, :phoneNumber, :avatar, :accountType)"   ;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("surname", user.getSurname())
                .addValue("age", user.getAge())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("phoneNumber", user.getPhoneNumber())
                .addValue("avatar", user.getAvatar())
                .addValue("accountType", user.getAccountType());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, params, keyHolder);

        Number generatedId = keyHolder.getKey();
        if (generatedId != null) {
            user.setId(generatedId.intValue());
        }
        return user;
    }

    public void updateAvatar(int userId, String avatarPath) {
        String sql = "UPDATE users SET avatar = ? WHERE id = ?";
        jdbcTemplate.update(sql, avatarPath, userId);
    }

    public Optional<User> findApplicantByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ? AND account_type = 'APPLICANT'";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), email);
        return Optional.ofNullable(DataAccessUtils.singleResult(users));
    }

    public Optional<User> findEmployerByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ? AND account_type = 'EMPLOYER'";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), email);
        return Optional.ofNullable(DataAccessUtils.singleResult(users));
    }

}
