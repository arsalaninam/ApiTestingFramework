package com.abc.pojo.todo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * POJOs for All Todos API Response
 * http://<domain>/todos
 * e.g. http://jsonplaceholder.typicode.com/todos
 *
 * @author Arsalan Inam
 */
@Getter
@Setter
public class AllTodos {

    List<SingleTodo> listOfTodos;

    @Override
    public String toString() {
        return "AllTodos [listOfTodos=" + listOfTodos + "]";
    }
}