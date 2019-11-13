package com.abc.pojo.comment;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * POJOs for All Comments API Response
 * http://<domain>/comments
 * e.g. http://jsonplaceholder.typicode.com/comments
 *
 * @author Arsalan Inam
 */
@Getter
@Setter
public class AllComments {

    List<SingleComment> listOfComments;

    @Override
    public String toString() {
        return "AllComments [listOfComments=" + listOfComments + "]";
    }
}