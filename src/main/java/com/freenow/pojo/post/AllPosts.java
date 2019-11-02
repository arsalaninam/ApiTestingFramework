package com.freenow.pojo.post;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AllPosts {

    List<SinglePost> listOfPosts;

    @Override
    public String toString() {
        return "AllPosts [listOfPosts=" + listOfPosts + "]";
    }
}
