package com.abc.pojo.album;

import lombok.Getter;
import lombok.Setter;

/**
 * POJOs for Single Album by id API Response
 * https://<domain>/albums/<id>
 * e.g. https://jsonplaceholder.typicode.com/albums/1
 *
 * @author Arsalan Inam
 */
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
