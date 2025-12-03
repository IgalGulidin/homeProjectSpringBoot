package com.poll_service.poll_service.service;

import com.poll_service.poll_service.model.Poll;

import java.util.List;

public interface PollService {
    Long createPoll(Poll poll);
    Poll getPollById(Long id);
    void updatePoll(Poll poll);
    List<Poll> getAllPolls();
    void deletePoll(Long id);
}
