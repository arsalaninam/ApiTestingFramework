package com.freenow.pojo.photo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AllPhotos {

    List<SinglePhoto> listOfPhotos;

    @Override
    public String toString() {
        return "AllPhotos [listOfPhotos=" + listOfPhotos + "]";
    }
}
