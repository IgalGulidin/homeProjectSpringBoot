package com.poll_service.poll_service.controller;

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
}
