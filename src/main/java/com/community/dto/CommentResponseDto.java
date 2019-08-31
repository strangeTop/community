package com.community.dto;

import com.community.model.Comment;
import com.community.model.User;

import java.util.List;

public class CommentResponseDto {
    private User user;
    private Comment comment;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

}
