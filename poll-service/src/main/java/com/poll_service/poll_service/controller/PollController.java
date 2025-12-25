package com.poll_service.poll_service.controller;

import com.poll_service.poll_service.dto.AllPollsStatsResponse;
import com.poll_service.poll_service.dto.OptionCountResponse;
import com.poll_service.poll_service.dto.SubmitAnswerRequest;
import com.poll_service.poll_service.dto.UserAnswerResponse;
import com.poll_service.poll_service.model.Poll;
import com.poll_service.poll_service.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/poll")
public class PollController {

    @Autowired
    private PollService pollService;

    @PostMapping("/create")
    public Long createPoll(@RequestBody Poll poll) {
        return pollService.createPoll(poll);
    }

    @GetMapping("/{id}")
    public Poll getPollById(@PathVariable Long id) {
        return pollService.getPollById(id);
    }

    @PutMapping("/update")
    public void updatePoll(@RequestBody Poll poll) {
        pollService.updatePoll(poll);
    }

    @GetMapping("/all")
    public List<Poll> getAllPolls() {
        return pollService.getAllPolls();
    }

    @DeleteMapping("/delete/{id}")
    public void deletePollById(@PathVariable Long id) {
        pollService.deletePoll(id);
    }

    @PostMapping("/answer/submit")
    public void submitAnswer(@RequestBody SubmitAnswerRequest request) throws Exception {
        pollService.submitAnswer(request);
    }

    @GetMapping("/{pollId}/stats")
    public OptionCountResponse getStats(@PathVariable Long pollId) {
        return pollService.getOptionCounts(pollId);
    }

    @GetMapping("/{pollId}/answers/count")
    public Long getTotalAnswersCount(@PathVariable Long pollId) {
        return pollService.getTotalAnswersCount(pollId);
    }

    @GetMapping("/answers/user/{userId}")
    public List<UserAnswerResponse> getUserAnswers(@PathVariable Long userId) {
        return pollService.getAnswerByUser(userId);
    }

    @GetMapping("/stats/all")
    public List<AllPollsStatsResponse> getAllStats() throws Exception{
        return pollService.getAllPollsStats();
    }

    @GetMapping("/answers/user/{userId}/count")
    public Long getUserAnsweredCount(@PathVariable Long userId) {
        return pollService.getTotalAnsweredPollsByUser(userId);
    }

    @DeleteMapping("/answers/user/{userId}")
    public void deleteAnswersByUser(@PathVariable Long userId) {
        pollService.deleteAnswersByUser(userId);
    }
}
