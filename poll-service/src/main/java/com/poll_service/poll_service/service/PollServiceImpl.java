package com.poll_service.poll_service.service;

import com.poll_service.poll_service.client.UserClient;
import com.poll_service.poll_service.dto.AllPollsStatsResponse;
import com.poll_service.poll_service.dto.PollStatsResponse;
import com.poll_service.poll_service.dto.SubmitAnswerRequest;
import com.poll_service.poll_service.dto.UserAnswerResponse;
import com.poll_service.poll_service.exception.PollNotFoundException;
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
    public void submitAnswer(SubmitAnswerRequest request) throws Exception {
        var user = userClient.getUserById(request.getUserId());
        if (user == null) {
            throw  new Exception("User does not exist");
        }

        Poll poll = pollRepository.getPollById(request.getPollId());
        if (poll == null) {
            throw new Exception("Poll does not exist");
        }

        if (request.getSelectedOption() < 1 || request.getSelectedOption() > 4) {
            throw new Exception("Invalid option");
        }

        pollAnswerRepository.saveAnswer(
                new PollAnswer(
                        request.getUserId(),
                        request.getPollId(),
                        request.getSelectedOption()
                )
        );
    }

    @Override
    public PollStatsResponse getPollStats(Long pollId) throws Exception {
        Poll poll = pollRepository.getPollById(pollId);
        if (poll == null) {
            throw new Exception("Poll not found");
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
        List<PollAnswer> answers = pollAnswerRepository.findByUserId(userId);

        List<UserAnswerResponse> result = new ArrayList<>();

        for (PollAnswer answer : answers) {
            Poll poll = pollRepository.getPollById(answer.getPollId());
            if (poll == null) continue;

            UserAnswerResponse userAnswerResponse = new UserAnswerResponse();
            userAnswerResponse.setPollId(poll.getId());
            userAnswerResponse.setPollTitle(poll.getTitle());
            userAnswerResponse.setSelectedOption(answer.getSelectedOption());

            result.add(userAnswerResponse);
        }


        return result;
    }

    @Override
    public Integer countAnswersByUser(Long userId) {
        return pollAnswerRepository.findByUserId(userId).size();
    }

    @Override
    public List<AllPollsStatsResponse> getAllPollsStats() throws Exception {
        List<Poll> polls = pollRepository.getAllPolls();
        List<AllPollsStatsResponse> result = new ArrayList<>();

        for (Poll poll : polls) {
            PollStatsResponse stats = getPollStats(poll.getId());

            AllPollsStatsResponse allPollsStatsResponse = new AllPollsStatsResponse();
            allPollsStatsResponse.setPollId(poll.getId());
            allPollsStatsResponse.setTitle(poll.getTitle());
            allPollsStatsResponse.setOption1Count(stats.getOption1Count());
            allPollsStatsResponse.setOption2Count(stats.getOption2Count());
            allPollsStatsResponse.setOption3Count(stats.getOption3Count());
            allPollsStatsResponse.setOption4Count(stats.getOption4Count());

            result.add(allPollsStatsResponse);
        }

        return result;
    }

    @Override
    public void deleteAnswersByUser(Long userId) {
        pollAnswerRepository.deleteByUserId(userId);
    }

}
