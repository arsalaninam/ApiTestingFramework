package com.freenow.pojo.todo;

import lombok.Getter;
import lombok.Setter;

/**
 * POJOs for Single Todos by id API Response
 * https://<domain>/todos/<id>
 * e.g. https://jsonplaceholder.typicode.com/todos/1
 *
 * @author Arsalan Inam
 */
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
