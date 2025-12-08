package com.poll_service.poll_service.model;

import jakarta.persistence.*;


@Entity
@Table(name = "poll")
public class Poll {

    @Id
    private Long id;

    @Column(name = "question_title")
    private String questionTitle;

    @Column(name = "option_one")
    private String optionOne;

    @Column(name = "option_two")
    private String optionTwo;

    @Column(name = "option_three")
    private String optionThree;

    @Column(name = "option_four")
    private String optionFour;

    public Poll() {}

    public Poll(Long id, String questionTitle, String optionOne, String optionTwo, String optionThree, String optionFour) {
        this.id = id;
        this.questionTitle = questionTitle;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
        this.optionFour = optionFour;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.questionTitle;
    }

    public String getOptionOne() {
        return this.optionOne;
    }

    public String getOptionTwo() {
        return this.optionTwo;
    }

    public String getOptionThree() {
        return this.optionThree;
    }

    public String getOptionFour() {
        return this.optionFour;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public void setOptionOne(String optionOne) {
        this.optionOne = optionOne;
    }

    public void setOptionTwo(String optionTwo) {
        this.optionTwo = optionTwo;
    }

    public void setOptionThree(String optionThree) {
        this.optionThree = optionThree;
    }

    public void setOptionFour(String optionFour) {
        this.optionFour = optionFour;
    }
}
