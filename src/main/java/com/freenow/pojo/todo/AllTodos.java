package com.freenow.pojo.todo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AllTodos {

    List<SingleTodo> listOfTodos;

    @Override
    public String toString() {
        return "AllTodos [listOfTodos=" + listOfTodos + "]";
    }
}
