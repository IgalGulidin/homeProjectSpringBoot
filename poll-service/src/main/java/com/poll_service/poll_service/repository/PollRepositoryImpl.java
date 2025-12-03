package com.poll_service.poll_service.repository;

import com.poll_service.poll_service.model.Poll;
import com.poll_service.poll_service.repository.mapper.PollMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PollRepositoryImpl implements PollRepository{

    private static final String POLL_TABLE_NAME = "poll";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long createPoll(Poll poll) {
        String sql = "INSERT INTO " + POLL_TABLE_NAME + " (question_title, option_one, option_two, option_three, option_four)" +
                     "VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(
                sql,
                poll.getQuestionTitle(),
                poll.getOptionOne(),
                poll.getOptionTwo(),
                poll.getOptionThree(),
                poll.getOptionFour()
        );

        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
    }

    @Override
    public Poll getPollById(Long id) {
        String sql = "SELECT * FROM " + POLL_TABLE_NAME + " WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new PollMapper(), id);
    }

    @Override
    public void updatePoll(Poll poll) {
        String sql = "UPDATE " + POLL_TABLE_NAME + " SET question_title = ?, option_one = ?, option_two = ?, option_three = ?, option_four = ?" + " WHERE id = ?";

        jdbcTemplate.update(
                sql,
                poll.getQuestionTitle(),
                poll.getOptionOne(),
                poll.getOptionTwo(),
                poll.getOptionThree(),
                poll.getOptionFour(),
                poll.getId()
        );
    }

    @Override
    public List<Poll> getAllPolls() {
        String sql = "SELECT * FROM " + POLL_TABLE_NAME;

        return jdbcTemplate.query(sql, new PollMapper());
    }

    @Override
    public void deletePoll(Long id) {
        String sql = "DELETE FROM " + POLL_TABLE_NAME + " WHERE id = ?";

        jdbcTemplate.update(
                sql,
                id
        );
    }
}
