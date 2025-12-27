package com.poll_service.poll_service.service;

import com.poll_service.poll_service.client.UserClient;
import com.poll_service.poll_service.dto.*;
import com.poll_service.poll_service.exception.InvalidOptionException;
import com.poll_service.poll_service.exception.PollNotFoundException;
import com.poll_service.poll_service.exception.UserNotFoundException;
import com.poll_service.poll_service.model.Poll;
import com.poll_service.poll_service.model.PollAnswer;
import com.poll_service.poll_service.repository.PollAnswerRepository;
import com.poll_service.poll_service.repository.PollRepository;
import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PollServiceImpl implements PollService{

    @Autowired
    private UserClient userClient;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private PollAnswerRepository pollAnswerRepository;

    @Override
    public Long createPoll(Poll poll) {
        return pollRepository.createPoll(poll);
    }

    @Override
    public Poll getPollById(Long id) {
        Poll poll = pollRepository.getPollById(id);

        if (poll == null) {
            throw new PollNotFoundException("Poll does not exist");
        }

        return poll;
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

    @Override
    public void submitAnswer(SubmitAnswerRequest request) {

        int option = request.getSelectedOption();
        if (option < 1 || option > 4) {
            throw new InvalidOptionException("Invalid option selected (must be 1-4)");
        }

        try {
            userClient.getUserById(request.getUserId());
        } catch (feign.FeignException.NotFound e) {
            throw new UserNotFoundException("User does not exist: " + request.getUserId());
        }

        Poll poll = pollRepository.getPollById(request.getPollId());
        if (poll == null) {
            throw new PollNotFoundException("Poll does not exist: " + request.getPollId());
        }

        PollAnswer answer = new PollAnswer(
                request.getUserId(),
                request.getPollId(),
                option
        );

        pollAnswerRepository.saveAnswer(answer);
    }

    @Override
    public PollStatsResponse getPollStats(Long pollId) throws PollNotFoundException {
        Poll poll = pollRepository.getPollById(pollId);
        if (poll == null) {
            throw new PollNotFoundException("Poll not found");
        }

        List<PollAnswer> answers = pollAnswerRepository.findByPollId(pollId);

        int c1 = 0, c2 = 0, c3 = 0, c4 = 0;
        for (PollAnswer answer : answers) {
            switch (answer.getSelectedOption()) {
                case 1 -> c1++;
                case 2 -> c2++;
                case 3 -> c3++;
                case 4 -> c4++;
            }
        }

        PollStatsResponse response = new PollStatsResponse();
        response.setPollId(pollId);
        response.setTitle(poll.getTitle());
        response.setOption1Count(c1);
        response.setOption2Count(c2);
        response.setOption3Count(c3);
        response.setOption4Count(c4);

        return response;
    }

    @Override
    public List<UserAnswerResponse> getAnswerByUser(Long userId) {
        return pollAnswerRepository.findAnswersByUserId(userId);
    }

    @Override
    public Integer countAnswersByUser(Long userId) {
        return pollAnswerRepository.countByUserId(userId).intValue();
    }

    @Override
    public List<AllPollsStatsResponse> getAllPollsStats() {
        List<Poll> polls = pollRepository.getAllPolls();
        List<AllPollsStatsResponse> result = new ArrayList<>();

        for (Poll poll : polls) {
            OptionCountResponse counts =
                    pollAnswerRepository.getOptionCountsByPollId(poll.getId());

            AllPollsStatsResponse response = new AllPollsStatsResponse();
            response.setPollId(poll.getId());
            response.setTitle(poll.getTitle());
            response.setOption1Count(counts.getOption1Count());
            response.setOption2Count(counts.getOption2Count());
            response.setOption3Count(counts.getOption3Count());
            response.setOption4Count(counts.getOption4Count());

            result.add(response);
        }

        return result;
    }

    @Override
    public void deleteAnswersByUser(Long userId) {
        pollAnswerRepository.deleteByUserId(userId);
    }


    @Override
    public OptionCountResponse getOptionCounts(Long pollId) {
        Poll poll = pollRepository.getPollById(pollId);
        if (poll == null) {
            throw new PollNotFoundException("Poll does not exist" + pollId);
        }

        return pollAnswerRepository.getOptionCountsByPollId(pollId);
    }

    @Override
    public Long getTotalAnswersCount(Long pollId) {

        Poll poll = pollRepository.getPollById(pollId);
        if (poll == null) {
            throw new PollNotFoundException("Poll does not exist" + pollId);
        }

        return pollAnswerRepository.countAnswersByPollId(pollId);
    }

    @Override
    public Long getTotalAnsweredPollsByUser(Long userId) {
        return pollAnswerRepository.countAnswersByUserId(userId);
    }
}
