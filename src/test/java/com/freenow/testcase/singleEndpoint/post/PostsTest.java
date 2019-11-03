package com.freenow.testcase.singleEndpoint.post;

import com.freenow.businesslayer.post.AllPostsBusinessLogic;
import com.freenow.businesslayer.post.SinglePostBusinessLogic;
import com.freenow.data.dataprovider.CommonDataProvider;
import com.freenow.data.dataprovider.PostDataProvider;
import com.freenow.pojo.post.AllPosts;
import com.freenow.pojo.post.SinglePost;
import com.freenow.testcase.singleEndpoint.SingleEndpointCommon;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.freenow.constant.ResponseCodeConstant.STATUS_CODE_404;
import static com.freenow.constant.ScenarioNameConstant.*;
import static com.freenow.constant.ServiceConstant.POSTS_ENDPOINT;
import static io.restassured.RestAssured.given;

/**
 * Test Class to perform Posts Endpoint test cases
 * Endpoints that are used in the following Test Class are
 * - https://<domain>/posts
 * - https://<domain>/posts/{id}
 *
 * @author Arsalan Inam
 */

public class PostsTest extends SingleEndpointCommon {

    private static final Logger log = LoggerFactory.getLogger(PostsTest.class);
    private String postsEndpoint = prop.getProperty(POSTS_ENDPOINT);

    /**********************************************************
     * Send a GET request to /posts. Validate that response
     * has HTTP status code 200 and Content Type JSON
     *********************************************************/

    @Test
    public void testResponseStatusCode200AndContentTypeJSON() {
        log.info(VALIDATE_STATUS_CODE_200_AND_CONTENT_TYPE_JSON + postsEndpoint);
        given().
                spec(requestSpecification).
                when().
                get(postsEndpoint).
                then().
                spec(responseSpecification);
    }

    /***********************************************************
     * Send a GET request to /posts. Validate that
     * - response body contains list of 100 posts
     ***********************************************************/

    @Test(dependsOnMethods = "testResponseStatusCode200AndContentTypeJSON")
    public void fetchListOfPostsAndAssertSize() {
        log.info(VALIDATE_LIST_OF_ITEM + postsEndpoint);

        AllPosts allPosts = AllPostsBusinessLogic.getAllPosts();
        List<SinglePost> allPostsList = allPosts.getListOfPosts();
        Assert.assertEquals(allPostsList.size(), 100);
    }

    /************************************************************
     * Send a GET request to /posts/{posts} and Validate
     * - response has HTTP status code 200 & Content Type is JSON
     ***********************************************************/

    @Test(dataProvider = "validId", dataProviderClass = CommonDataProvider.class)
    public void testResponseCodeAndContentType(int id) {
        log.info(VALIDATE_STATUS_CODE_200_AND_CONTENT_TYPE_JSON + postsEndpoint + id);
        given().
                spec(requestSpecification).
                when().
                get(postsEndpoint + id).
                then().
                spec(responseSpecification);
    }

    /******************************************************
     * Send a GET request to /posts/{postsId} and Validate
     * - response returns the expected id, title & body
     ******************************************************/

    @Test(dependsOnMethods = {"testResponseCodeAndContentType"},
            dataProvider = "validPostsIdWithTitle", dataProviderClass = PostDataProvider.class)
    public void testResponseBodyWithIdTitleAndBody(int id, String title) {
        log.info(VALIDATE_RESPONSE_BODY + postsEndpoint + id);

        SinglePost singlePost = SinglePostBusinessLogic.getSinglePostById(id);
        softAssert.assertEquals(singlePost.getId(), id);
        softAssert.assertEquals(singlePost.getTitle(), title);
        softAssert.assertNotNull(singlePost.getBody());
        softAssert.assertAll();
    }

    /***************************************************************
     * Send a GET request to /posts/{postsId} with invalid postsId
     * Validate that response has HTTP status code 404
     **************************************************************/

    @Test(dataProvider = "invalidId", dataProviderClass = CommonDataProvider.class)
    public void testResponseCodeWithInvalidPostsId(int id) {
        log.info(VALIDATE_STATUS_CODE_404 + postsEndpoint + id);
        Response response = given().
                spec(requestSpecification).
                when().
                get(postsEndpoint + id);
        Assert.assertEquals(response.statusCode(), STATUS_CODE_404);
    }
}
