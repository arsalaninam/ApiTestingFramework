package com.freenow.businesslayer.album;

import com.freenow.pojo.album.AllAlbums;
import com.freenow.pojo.album.SingleAlbum;
import com.freenow.util.PropertyReader;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static com.freenow.constant.ServiceConstant.*;
import static io.restassured.RestAssured.when;

public class AllAlbumsBusinessLogic extends PropertyReader {

    private static final Logger log = LoggerFactory.getLogger(AllAlbumsBusinessLogic.class);

    /**
     * Fetch list of all albums
     * e.g. https://jsonplaceholder.typicode.com/albums
     */
    public static AllAlbums getAllAlbums() {
        String baseUrl = prop.getProperty(BASE_URL);
        String albumsEndpoint = prop.getProperty(ALBUMS_ENDPOINT);
        String url = baseUrl + albumsEndpoint;
        log.info("URL to be hit:" + url);

        Response response = when().get(url);
        List<SingleAlbum> allSingleAlbums = Arrays.asList(response.getBody().as(SingleAlbum[].class));

        AllAlbums allAlbums = new AllAlbums();
        allAlbums.setListOfAlbums(allSingleAlbums);
        return allAlbums;
    }

    /**
     * Fetch list of all albums json set respective to particular userId
     * e.g. https://jsonplaceholder.typicode.com/albums?userId=1
     */
    public static AllAlbums getAllAlbumsForUserId(int userId) {
        String baseUrl = prop.getProperty(BASE_URL);
        String albumsEndpointQueryParamUserId = prop.getProperty(ALBUMS_ENDPOINT_QUERY_PARAM_USER_ID);
        String url = baseUrl + albumsEndpointQueryParamUserId + userId;
        log.info("URL to be hit:" + url);

        Response response = when().get(url);
        List<SingleAlbum> allSingleAlbums = Arrays.asList(response.getBody().as(SingleAlbum[].class));

        AllAlbums allAlbums = new AllAlbums();
        allAlbums.setListOfAlbums(allSingleAlbums);
        return allAlbums;
    }
}
