package com.poll_service.poll_service.dto;

public class SubmitAnswerRequest {

    private Long userId;
    private Long pollId;
    private Integer selectedOption;

    public SubmitAnswerRequest() {};

    public Long getUserId() {
        return this.userId;
    }

    public Long getPollId() {
        return this.pollId;
    }

    public Integer getSelectedOption() {
        return this.selectedOption;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    public void setSelectedOption(Integer selectedOption) {
        this.selectedOption = selectedOption;
    }
}
