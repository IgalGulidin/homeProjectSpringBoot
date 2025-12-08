package com.poll_service.poll_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "poll_answers")
public class PollAnswer {

    @Id
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "poll_id")
    private Long pollId;

    @Column(name = "selected_option")
    private Integer selectedOption;

    public PollAnswer() {};

    public PollAnswer(Long userId, Long pollId, Integer selectedOption) {
        this.userId = userId;
        this.pollId = pollId;
        this.selectedOption = selectedOption;
    }


    public Long getId() {
        return this.id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Long getPollId() {
        return this.pollId;
    }

    public Integer getSelectedOption() {
        return this.selectedOption;
    }

    public void setId(Long id) {
        this.id = id;
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
