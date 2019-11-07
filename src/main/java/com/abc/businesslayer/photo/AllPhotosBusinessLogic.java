package com.abc.businesslayer.photo;

import com.abc.pojo.photo.AllPhotos;
import com.abc.pojo.photo.SinglePhoto;
import com.abc.util.PropertyReader;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static com.abc.constant.ServiceConstant.*;
import static io.restassured.RestAssured.when;

public class AllPhotosBusinessLogic extends PropertyReader {

    private static final Logger log = LoggerFactory.getLogger(AllPhotosBusinessLogic.class);

    /**
     * Fetch list of all photos
     * e.g. https://jsonplaceholder.typicode.com/photos
     */
    public static AllPhotos getAllPhotos() {
        String baseUrl = prop.getProperty(BASE_URL);
        String photosEndpoint = prop.getProperty(PHOTOS_ENDPOINT);
        String url = baseUrl + photosEndpoint;
        log.info("URL to be hit:" + url);

        Response response = when().get(url);
        List<SinglePhoto> allSinglePhotos = Arrays.asList(response.getBody().as(SinglePhoto[].class));

        AllPhotos allPhotos = new AllPhotos();
        allPhotos.setListOfPhotos(allSinglePhotos);
        return allPhotos;
    }

    /**
     * Fetch list of all photos json set respective to particular albumId
     * e.g. https://jsonplaceholder.typicode.com/photos?albumId=1
     */
    public static AllPhotos getAllPhotosForAlbumId(int albumId) {
        String baseUrl = prop.getProperty(BASE_URL);
        String photosEndpointQueryParamAlbumId = prop.getProperty(PHOTOS_ENDPOINT_QUERY_PARAM_ALBUM_ID);
        String url = baseUrl + photosEndpointQueryParamAlbumId + albumId;
        log.info("URL to be hit:" + url);

        Response response = when().get(url);
        List<SinglePhoto> allSinglePhotos = Arrays.asList(response.getBody().as(SinglePhoto[].class));

        AllPhotos allPhotos = new AllPhotos();
        allPhotos.setListOfPhotos(allSinglePhotos);
        return allPhotos;
    }
}
