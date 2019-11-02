package com.freenow.pojo.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleComment {

    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

    @Override
    public String toString() {
        return "ClassPojo [postId = " + postId + ", id = " + id + ", name = " + name + ", email = " + email + ", body = " + body + "]";
    }
}
