package com.user_service.user_service.repository;

import com.user_service.user_service.model.User;
import com.user_service.user_service.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private static final String USER_TABLE_NAME = "users";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long createUser(User user) {
        String sql = "INSERT INTO users (first_name, last_name, email, age, address)" +
                     "VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(
                sql,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getAge(),
                user.getAddress()
        );

        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE " + USER_TABLE_NAME + " SET first_name = ?, last_name = ?, email = ?, age = ?, address = ? " + "WHERE id = ?";

        jdbcTemplate.update(sql,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getAge(),
                user.getAddress(),
                user.getId()
        );
    }

    @Override
    public User getUserById(Long id) {
        String sql = "SELECT * FROM " + USER_TABLE_NAME + " WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM " + USER_TABLE_NAME;

        return jdbcTemplate.query(sql, new  UserMapper());
    }

    @Override
    public void deleteUser(Long id) {
        String sql = "DELETE FROM " + USER_TABLE_NAME + " WHERE id = ?";

        jdbcTemplate.update(sql,
                id
        );
    }
}
