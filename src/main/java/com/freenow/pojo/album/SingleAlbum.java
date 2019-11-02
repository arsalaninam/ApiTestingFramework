package com.freenow.pojo.album;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleAlbum {

    private int userId;
    private int id;
    private String title;

    @Override
    public String toString() {
        return "ClassPojo [userId = " + userId + ", id = " + id + ", title = " + title + "]";
    }
}
