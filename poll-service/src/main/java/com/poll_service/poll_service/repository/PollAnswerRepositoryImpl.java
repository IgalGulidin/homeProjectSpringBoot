package com.poll_service.poll_service.repository;

import com.poll_service.poll_service.dto.OptionCountResponse;
import com.poll_service.poll_service.dto.UserAnswerResponse;
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

    @Override
    public OptionCountResponse getOptionCountsByPollId(Long pollId) {

        String sql =
                "SELECT poll_id, " +
                "SUM(CASE WHEN selected_option = 1 THEN 1 ELSE 0 END) AS option1_count, " +
                "SUM(CASE WHEN selected_option = 2 THEN 1 ELSE 0 END) AS option2_count, " +
                "SUM(CASE WHEN selected_option = 3 THEN 1 ELSE 0 END) AS option3_count, " +
                "SUM(CASE WHEN selected_option = 4 THEN 1 ELSE 0 END) AS option4_count, " +
                "COUNT(*) AS total_count " +
                "FROM poll_answers " +
                "WHERE poll_id = ? " +
                "GROUP BY poll_id";

        List<OptionCountResponse> results = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new OptionCountResponse(
                        rs.getLong("poll_id"),
                        rs.getInt("option1_count"),
                        rs.getInt("option2_count"),
                        rs.getInt("option3_count"),
                        rs.getInt("option4_count"),
                        rs.getInt("total_count")
                ),
                pollId
        );

        return results.isEmpty() ? new OptionCountResponse(pollId, 0, 0, 0, 0 ,0) : results.get(0);
    }

    @Override
    public Long countAnswersByPollId(Long pollId) {
        String sql = "SELECT COUNT(*) FROM poll_answers WHERE poll_id = ?";
        Long count = jdbcTemplate.queryForObject(sql, Long.class, pollId);
        return (count == null) ? 0 : count;
    }

    @Override
    public List<UserAnswerResponse> findAnswersByUserId(Long userId) {

        String sql =
                "SELECT poll_answers.poll_id, poll.question_title, poll_answers.selected_option " +
                "FROM poll_answers " +
                "JOIN poll ON poll_answers.poll_id = poll.id " +
                "WHERE poll_answers.user_id = ?";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {
                    UserAnswerResponse response = new UserAnswerResponse();
                    response.setPollId(rs.getLong("poll_id"));
                    response.setPollTitle(rs.getString("question_title"));
                    response.setSelectedOption(rs.getInt("selected_option"));
                    return response;
                },
                userId
        );
    }

    @Override
    public Long countByUserId(Long userId) {

        String sql = "SELECT COUNT(*) FROM poll_answers WHERE user_id = ?";
        Long count = jdbcTemplate.queryForObject(sql, Long.class, userId);
        return (count == null) ? 0 : count;
    }

    @Override
    public Long countAnswersByUserId(Long userId) {
        String sql = "SELECT COUNT(*) FROM poll_answers WHERE user_id = ?";
        Long count = jdbcTemplate.queryForObject(sql, Long.class, userId);
        return (count == null) ? 0 : count;
    }
    
}
