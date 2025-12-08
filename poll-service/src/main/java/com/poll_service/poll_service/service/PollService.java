package com.poll_service.poll_service.service;

import com.poll_service.poll_service.dto.AllPollsStatsResponse;
import com.poll_service.poll_service.dto.PollStatsResponse;
import com.poll_service.poll_service.dto.SubmitAnswerRequest;
import com.poll_service.poll_service.dto.UserAnswerResponse;
import com.poll_service.poll_service.model.Poll;

import java.util.List;

public interface PollService {
    Long createPoll(Poll poll);
    Poll getPollById(Long id);
    void updatePoll(Poll poll);
    List<Poll> getAllPolls();
    void deletePoll(Long id);
    void submitAnswer(SubmitAnswerRequest request) throws Exception;
    PollStatsResponse getPollStats(Long pollId) throws Exception;
    List<UserAnswerResponse> getAnswerByUser(Long userId);
    Integer countAnswersByUser(Long userId);
    List<AllPollsStatsResponse> getAllPollsStats() throws Exception;
    void deleteAnswersByUser(Long userId);
}
