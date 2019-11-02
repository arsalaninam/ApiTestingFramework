package com.freenow.pojo.todo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleTodo {

    private int userId;
    private int id;
    private String title;
    private String completed;

    @Override
    public String toString() {
        return "ClassPojo [userId = " + userId + ", id = " + id + ", title = " + title + ", completed = " + completed + "]";
    }
}
