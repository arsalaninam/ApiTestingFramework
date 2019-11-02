package com.freenow.businesslayer.post;

import com.freenow.pojo.post.SinglePost;
import com.freenow.util.PropertyReader;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.freenow.constant.ServiceConstant.BASE_URL;
import static com.freenow.constant.ServiceConstant.POSTS_ENDPOINT;
import static io.restassured.RestAssured.when;

public class SinglePostBusinessLogic extends PropertyReader {

    private static final Logger log = LoggerFactory.getLogger(SinglePostBusinessLogic.class);

    /**
     * Extract data as Response object
     * Populate Single Post Response POJOs
     * e.g. https://jsonplaceholder.typicode.com/posts/1
     *
     * @param id - post id to fetch a single post
     */
    public static SinglePost getSinglePostById(String id) {
        String baseUrl = prop.getProperty(BASE_URL);
        String postsEndpoint = prop.getProperty(POSTS_ENDPOINT);
        String url = baseUrl + postsEndpoint + id;
        log.info("API to be hit:" + url);

        Response response = when().get(url);
        SinglePost singlePost = response.getBody().as(SinglePost.class);
        return singlePost;
    }
}
