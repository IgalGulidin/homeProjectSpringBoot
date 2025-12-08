package com.poll_service.poll_service.repository;

import com.poll_service.poll_service.model.PollAnswer;

import java.util.List;

public interface PollAnswerRepository {
    void saveAnswer(PollAnswer answer);
    void deleteByUserId(Long userId);
    List<PollAnswer> findByUserId(Long userId);
    List<PollAnswer> findByPollId(Long pollId);
}
