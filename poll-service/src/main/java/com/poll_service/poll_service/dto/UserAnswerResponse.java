package com.poll_service.poll_service.dto;

public class UserAnswerResponse {

    private Long pollId;
    private String pollTitle;
    private Integer selectedOption;

    public UserAnswerResponse () {};

    public Long getPollId() {
        return this.pollId;
    }

    public String getPollTitle() {
        return this.pollTitle;
    }

    public Integer getSelectedOption() {
        return this.selectedOption;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    public void setPollTitle(String pollTitle) {
        this.pollTitle = pollTitle;
    }

    public void setSelectedOption(Integer selectedOption) {
        this.selectedOption = selectedOption;
    }
}
