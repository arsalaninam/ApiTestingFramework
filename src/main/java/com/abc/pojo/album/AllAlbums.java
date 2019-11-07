package com.abc.pojo.album;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * POJOs for All Albums API Response
 * http://<domain>/albums
 * e.g. http://jsonplaceholder.typicode.com/albums
 *
 * @author Arsalan Inam
 */
@Getter
@Setter
public class AllAlbums {

    List<SingleAlbum> listOfAlbums;

    @Override
    public String toString() {
        return "AllAlbums [listOfAlbums=" + listOfAlbums + "]";
    }
}
