package com.poll_service.poll_service.dto;

import com.poll_service.poll_service.model.PollAnswer;

public class OptionCountResponse {
    private Long pollId;
    private int option1Count;
    private int option2Count;
    private int option3Count;
    private int option4Count;
    private int totalCount;

    public OptionCountResponse() {}

    public OptionCountResponse(Long pollId, int option1Count, int option2Count, int option3Count, int option4Count, int totalCount) {
        this.pollId = pollId;
        this.option1Count = option1Count;
        this.option2Count = option2Count;
        this.option3Count = option3Count;
        this.option4Count = option4Count;
        this.totalCount = totalCount;
    }

    public Long getPollId() {
        return this.pollId;
    }

    public int getOption1Count() {
        return this.option1Count;
    }

    public int getOption2Count() {
        return this.option2Count;
    }

    public int getOption3Count() {
        return this.option3Count;
    }

    public int getOption4Count() {
        return this.option4Count;
    }

    public int getTotalCount() {
        return this.totalCount;
    }
}
