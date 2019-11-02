package com.freenow.pojo.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AllUsers {

    List<SingleUser> listOfUsers;

    @Override
    public String toString() {
        return "AllUsers [listOfUsers=" + listOfUsers + "]";
    }
}
