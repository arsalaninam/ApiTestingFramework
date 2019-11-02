package com.freenow.pojo.comment;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AllComments {

    List<SingleComment> listOfComments;

    @Override
    public String toString() {
        return "AllComments [listOfComments=" + listOfComments + "]";
    }
}
