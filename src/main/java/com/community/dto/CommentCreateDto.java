package com.community.dto;

public class CommentCreateDto {
    private Integer type;
    private Integer commentTo;
    private String commentText;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public Integer getCommentTo() {
        return commentTo;
    }

    public void setCommentTo(Integer commentTo) {
        this.commentTo = commentTo;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
