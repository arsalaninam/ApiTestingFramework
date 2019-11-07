package com.freenow.pojo.photo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * POJOs for All Photos API Response
 * http://<domain>/photos
 * e.g. http://jsonplaceholder.typicode.com/photos
 *
 * @author Arsalan Inam
 */
@Getter
@Setter
public class AllPhotos {

    List<SinglePhoto> listOfPhotos;

    @Override
    public String toString() {
        return "AllPhotos [listOfPhotos=" + listOfPhotos + "]";
    }
}
