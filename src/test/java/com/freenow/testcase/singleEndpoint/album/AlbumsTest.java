package com.freenow.testcase.singleEndpoint.album;

import com.freenow.businesslayer.album.SingleAlbumBusinessLogic;
import com.freenow.data.dataprovider.AlbumDataProvider;
import com.freenow.data.dataprovider.CommonDataProvider;
import com.freenow.pojo.album.SingleAlbum;
import com.freenow.testcase.singleEndpoint.SingleEndpointCommon;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.freenow.constant.ResponseCodeConstant.STATUS_CODE_404;
import static com.freenow.constant.ScenarioNameConstant.*;
import static com.freenow.constant.ServiceConstant.ALBUMS_ENDPOINT;
import static io.restassured.RestAssured.given;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class AlbumsTest extends SingleEndpointCommon {

    private static final Logger log = LoggerFactory.getLogger(AlbumsTest.class);
    private String albumsEndpoint = prop.getProperty(ALBUMS_ENDPOINT);

    /**********************************************************
     * Send a GET request to /albums. Validate that response
     * has HTTP status code 200 and Content Type JSON
     *********************************************************/
    @Test
    public void testResponseStatusCode200AndContentTypeJSON() {
        log.info(VALIDATE_STATUS_CODE_200_AND_CONTENT_TYPE_JSON + albumsEndpoint);
        given().
                spec(requestSpecification).
                when().
                get(albumsEndpoint).
                then().
                spec(responseSpecification);
    }

    /***********************************************************
     * Send a GET request to /albums. Validate that
     * - response body contains list of 100 albums
     * - response has HTTP status code 200 and Content Type JSON
     ***********************************************************/
    @Test(dependsOnMethods = "testResponseStatusCode200AndContentTypeJSON")
    public void fetchListOfAlbumsAndAssertSize() {
        log.info(VALIDATE_LIST_OF_ITEM + albumsEndpoint);
        given().
                spec(requestSpecification).
                when().
                get(albumsEndpoint).
                then().
                assertThat().
                body("$", hasSize(100)).
                and().
                spec(responseSpecification);
    }

    /*************************************************************
     * Send a GET request to /albums/{albumsId} and Validate
     * - response has HTTP status code 200 & Content Type is JSON
     ************************************************************/
    @Test(dataProvider = "validId", dataProviderClass = CommonDataProvider.class)
    public void testResponseCodeAndContentType(int id) {
        log.info(VALIDATE_STATUS_CODE_200_AND_CONTENT_TYPE_JSON + albumsEndpoint + id);
        given().
                spec(requestSpecification).
                when().
                get(albumsEndpoint + id).
                then().
                spec(responseSpecification);
    }

    /*********************************************************
     * Send a GET request to /albums/{albumsId} and Validate
     * - response returns the expected id and title
     ********************************************************/
    @Test(dependsOnMethods = {"testResponseCodeAndContentType"},
            dataProvider = "validAlbumIdWithTitle", dataProviderClass = AlbumDataProvider.class)
    public void testResponseBodyWithIdAndTitle(int id, String title) {
        log.info(VALIDATE_RESPONSE_BODY + albumsEndpoint + id);

        SingleAlbum singleAlbum = SingleAlbumBusinessLogic.getSingleAlbumById(id);

        softAssert.assertEquals(singleAlbum.getId(), id);
        softAssert.assertEquals(singleAlbum.getTitle(), title);
        softAssert.assertAll();
    }

    /*******************************************************************
     * Send a GET request to /albums/{albumsId} with invalid albumsId
     * Validate that response has HTTP status code 404
     ******************************************************************/
    @Test(dataProvider = "invalidId", dataProviderClass = CommonDataProvider.class)
    public void testResponseCodeWithInvalidAlbumsId(int id) {
        log.info(VALIDATE_STATUS_CODE_404 + albumsEndpoint + id);
        Response response = given().spec(requestSpecification).when().get(albumsEndpoint + id);
        Assert.assertEquals(response.statusCode(), STATUS_CODE_404);
    }

}
