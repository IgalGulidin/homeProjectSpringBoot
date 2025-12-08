package com.poll_service.poll_service.repository.mapper;

import com.poll_service.poll_service.model.PollAnswer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PollAnswerMapper implements RowMapper<PollAnswer> {
    @Override
    public PollAnswer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new PollAnswer(
                rs.getLong("user_id"),
                rs.getLong("poll_id"),
                rs.getInt("selected_option")
        );
    }
}
