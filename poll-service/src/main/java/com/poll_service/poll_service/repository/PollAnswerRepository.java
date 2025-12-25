package com.poll_service.poll_service.repository;

import com.poll_service.poll_service.dto.OptionCountResponse;
import com.poll_service.poll_service.dto.UserAnswerResponse;
import com.poll_service.poll_service.model.PollAnswer;

import java.util.List;

public interface PollAnswerRepository {
    void saveAnswer(PollAnswer answer);
    void deleteByUserId(Long userId);
    List<PollAnswer> findByUserId(Long userId);
    List<PollAnswer> findByPollId(Long pollId);
    OptionCountResponse getOptionCountsByPollId(Long pollId);
    Long countAnswersByPollId(Long pollId);
    List<UserAnswerResponse> findAnswersByUserId(Long userId);
    Long countByUserId(Long userId);
    Long countAnswersByUserId(Long userId);
}
