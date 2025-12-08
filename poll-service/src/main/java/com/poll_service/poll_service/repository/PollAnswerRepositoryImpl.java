package com.poll_service.poll_service.repository;

import com.poll_service.poll_service.model.PollAnswer;
import com.poll_service.poll_service.repository.mapper.PollAnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PollAnswerRepositoryImpl implements PollAnswerRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void saveAnswer(PollAnswer answer) {
        String sql = "INSERT INTO poll_answers (user_id, poll_id, selected_option) VALUES (?, ?, ?)";

        jdbcTemplate.update(sql,
                answer.getUserId(),
                answer.getPollId(),
                answer.getSelectedOption()
                );
    }

    @Override
    public void deleteByUserId(Long userId) {
        String sql = "DELETE FROM poll_answers WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }

    @Override
    public List<PollAnswer> findByUserId(Long userId) {
        String sql = "SELECT * FROM poll_answers WHERE user_id = ?";
        return jdbcTemplate.query(sql, new PollAnswerMapper(), userId);
    }

    @Override
    public List<PollAnswer> findByPollId(Long pollId) {
        String sql = "SELECT * FROM poll_answers WHERE poll_id = ?";
        return jdbcTemplate.query(sql, new PollAnswerMapper(), pollId);
    }
}
