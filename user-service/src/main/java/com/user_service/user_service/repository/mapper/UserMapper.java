package com.user_service.user_service.repository.mapper;

import com.user_service.user_service.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getLong("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getInt("age"),
                rs.getString("address"),
                rs.getTimestamp("joining_date").toLocalDateTime().toLocalDate()
        );
    }
}
