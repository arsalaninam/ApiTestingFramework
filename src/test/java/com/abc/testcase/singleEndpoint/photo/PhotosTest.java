package com.abc.testcase.singleEndpoint.photo;

import com.abc.businesslayer.photo.AllPhotosBusinessLogic;
import com.abc.businesslayer.photo.SinglePhotoBusinessLogic;
import com.abc.data.dataprovider.CommonDataProvider;
import com.abc.data.dataprovider.PhotoDataProvider;
import com.abc.pojo.photo.AllPhotos;
import com.abc.pojo.photo.SinglePhoto;
import com.abc.testcase.singleEndpoint.SingleEndpointCommon;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.abc.constant.ScenarioNameConstant.*;
import static com.abc.constant.ServiceConstant.PHOTOS_ENDPOINT;
import static io.restassured.RestAssured.given;

/**
 * Test Class to perform Photos Endpoint test cases
 * Endpoints that are used in the following Test Class are
 * - https://<domain>/photos
 * - https://<domain>/photos/{id}
 *
 * @author Arsalan Inam
 */

public class PhotosTest extends SingleEndpointCommon {

    private static final Logger log = LoggerFactory.getLogger(PhotosTest.class);
    private String photosEndpoint = prop.getProperty(PHOTOS_ENDPOINT);

    /********************************************************
     * Send a GET request to /photos. Validate that response
     * has HTTP status code 200 and Content Type JSON
     *******************************************************/

    @Test
    public void testResponseStatusCode200AndContentTypeJSON() {
        log.info(VALIDATE_STATUS_CODE_200_AND_CONTENT_TYPE_JSON + photosEndpoint);
        given().
                spec(requestSpecification).
                when().
                get(photosEndpoint).
                then().
                spec(responseSpecification);
    }

    /***********************************************************
     * Send a GET request to /photos. Validate that
     * - response body contains list of 5000 photos
     ***********************************************************/

    @Test(dependsOnMethods = "testResponseStatusCode200AndContentTypeJSON")
    public void fetchListOfPhotosAndAssertSize() {
        log.info(VALIDATE_LIST_OF_ITEM + photosEndpoint);

        AllPhotos allPhotos = AllPhotosBusinessLogic.getAllPhotos();
        List<SinglePhoto> allPhotosList = allPhotos.getListOfPhotos();
        Assert.assertEquals(allPhotosList.size(), 5000);
    }

    /************************************************************
     * Send a GET request to /photos/{photosId} and Validate
     * - response has HTTP status code 200 & Content Type is JSON
     ***********************************************************/

    @Test(dataProvider = "validId", dataProviderClass = CommonDataProvider.class)
    public void testResponseCodeAndContentType(int id) {
        log.info(VALIDATE_STATUS_CODE_200_AND_CONTENT_TYPE_JSON + photosEndpoint + id);
        given().
                spec(requestSpecification).
                when().
                get(photosEndpoint + id).
                then().
                spec(responseSpecification);
    }

    /****************************************************************
     * Send a GET request to /photos/{photosId} and Validate
     * - response returns the expected id, title, url & thumbnailUrl
     ***************************************************************/

    @Test(dependsOnMethods = {"testResponseCodeAndContentType"},
            dataProvider = "validPhotoIdWithTitleAndUrl", dataProviderClass = PhotoDataProvider.class)
    public void testResponseBodyWithIdTitleUrlAndThumbnailUrl(int id, String title, String url) {
        log.info(VALIDATE_RESPONSE_BODY + photosEndpoint + id);

        SinglePhoto singlePhoto = SinglePhotoBusinessLogic.getSinglePhotoById(id);
        softAssert.assertEquals(singlePhoto.getId(), id);
        softAssert.assertEquals(singlePhoto.getTitle(), title);
        softAssert.assertEquals(singlePhoto.getUrl(), url);
        softAssert.assertNotNull(singlePhoto.getThumbnailUrl());
        softAssert.assertAll();
    }

    /******************************************************************
     * Send a GET request to /photos/{photosId} with invalid photosId
     * Validate that response has HTTP status code 404
     *****************************************************************/

    @Test(dataProvider = "invalidId", dataProviderClass = CommonDataProvider.class)
    public void testResponseCodeWithInvalidPhotosId(int id) {
        log.info(VALIDATE_STATUS_CODE_404 + photosEndpoint + id);
        Response response = given().
                spec(requestSpecification).
                when().
                get(photosEndpoint + id);
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_NOT_FOUND);
    }
}