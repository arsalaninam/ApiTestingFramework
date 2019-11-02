package com.freenow.pojo.photo;

import lombok.Getter;
import lombok.Setter;

/**
 * POJOs for Single Photo by id API Response
 * https://<domain>/photos/<id>
 * e.g. https://jsonplaceholder.typicode.com/photos/1
 *
 * @author Arsalan Inam
 */
@Getter
@Setter
public class SinglePhoto {

    private int albumId;
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;

    @Override
    public String toString() {
        return "ClassPojo [albumId = " + albumId + ", id = " + id + ", title = " + title + ", url = " + url + ", thumbnailUrl = " + thumbnailUrl + "]";
    }
}
