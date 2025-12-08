package com.poll_service.poll_service.dto;

public class AllPollsStatsResponse {

    private Long pollId;
    private String title;

    private Integer option1Count;
    private Integer option2Count;
    private Integer option3Count;
    private Integer option4Count;

    public AllPollsStatsResponse() {};

    public Long getPollId() {
        return this.pollId;
    }

    public String getTitle() {
        return this.title;
    }

    public Integer getOption1Count() {
        return this.option1Count;
    }

    public Integer getOption2Count() {
        return this.option2Count;
    }

    public Integer getOption3Count() {
        return this.option3Count;
    }

    public Integer getOption4Count() {
        return this.option4Count;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOption1Count(Integer option1Count) {
        this.option1Count = option1Count;
    }

    public void setOption2Count(Integer option2Count) {
        this.option2Count = option2Count;
    }

    public void setOption3Count(Integer option3Count) {
        this.option3Count = option3Count;
    }

    public void setOption4Count(Integer option4Count) {
        this.option4Count = option4Count;
    }
}
