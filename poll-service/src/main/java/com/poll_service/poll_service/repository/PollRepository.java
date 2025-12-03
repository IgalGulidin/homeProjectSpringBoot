package com.poll_service.poll_service.repository;

import com.poll_service.poll_service.model.Poll;

import java.util.List;

public interface PollRepository {
    Long createPoll(Poll poll);
    Poll getPollById(Long id);
    void updatePoll(Poll poll);
    List<Poll> getAllPolls();
    void deletePoll(Long id);
}
