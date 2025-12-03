package com.poll_service.poll_service.repository.mapper;

import com.poll_service.poll_service.model.Poll;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PollMapper implements RowMapper<Poll> {

    @Override
    public Poll mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Poll(
                rs.getLong("id"),
                rs.getString("question_title"),
                rs.getString("option_one"),
                rs.getString("option_two"),
                rs.getString("option_three"),
                rs.getString("option_four")
        );
    }
}
