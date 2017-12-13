package com.example.ratha.firebasedemo.entity;

import java.util.List;

/**
 * Created by ratha on 12/12/2017.
 */

public class UserComment {
    List<Comment> comments;

    public UserComment(){}

    public UserComment(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "UserComment{" +
                "comments=" + comments +
                '}';
    }

    public List<Comment> getComments() {
        return comments;
    }

    public UserComment setComments(List<Comment> comments) {
        this.comments = comments;
        return null;
    }
}
