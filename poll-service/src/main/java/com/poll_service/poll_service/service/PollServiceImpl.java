package com.poll_service.poll_service.service;

import com.poll_service.poll_service.model.Poll;
import com.poll_service.poll_service.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollServiceImpl implements PollService{

    @Autowired
    private PollRepository pollRepository;

    @Override
    public Long createPoll(Poll poll) {
        return pollRepository.createPoll(poll);
    }

    @Override
    public Poll getPollById(Long id) {
        return pollRepository.getPollById(id);
    }

    @Override
    public void updatePoll(Poll poll) {
        pollRepository.updatePoll(poll);
    }

    @Override
    public List<Poll> getAllPolls() {
        return pollRepository.getAllPolls();
    }

    @Override
    public void deletePoll(Long id) {
        pollRepository.deletePoll(id);
    }
}
