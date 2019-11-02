package com.freenow.pojo.album;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AllAlbums {

    List<SingleAlbum> listOfAlbums;

    @Override
    public String toString() {
        return "AllAlbums [listOfAlbums=" + listOfAlbums + "]";
    }
}
