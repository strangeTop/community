package com.community.dto;

import com.community.model.Comment;
import com.community.model.Question;
import com.community.model.User;

import java.util.ArrayList;
import java.util.List;

public class QuestionDto {
    private Question question;
    private User user;


    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
