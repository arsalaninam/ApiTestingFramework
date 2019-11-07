package com.freenow.businesslayer.album;

import com.freenow.pojo.album.SingleAlbum;
import com.freenow.util.PropertyReader;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.freenow.constant.ServiceConstant.ALBUMS_ENDPOINT;
import static com.freenow.constant.ServiceConstant.BASE_URL;
import static io.restassured.RestAssured.when;

public class SingleAlbumBusinessLogic extends PropertyReader {

    private static final Logger log = LoggerFactory.getLogger(SingleAlbumBusinessLogic.class);

    /**
     * Extract data as Response object
     * Populate Single Album Response POJOs
     * e.g. https://jsonplaceholder.typicode.com/albums/1
     *
     * @param id - album id to fetch a single album
     */
    public static SingleAlbum getSingleAlbumById(int id) {
        String baseUrl = prop.getProperty(BASE_URL);
        String albumsEndpoint = prop.getProperty(ALBUMS_ENDPOINT);
        String url = baseUrl + albumsEndpoint + id;
        log.info("API to be hit:" + url);

        Response response = when().get(url);
        SingleAlbum singleAlbum = response.getBody().as(SingleAlbum.class);
        log.info("Info: " + singleAlbum);
        return singleAlbum;
    }
}
