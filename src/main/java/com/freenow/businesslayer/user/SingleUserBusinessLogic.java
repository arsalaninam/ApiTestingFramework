package com.freenow.businesslayer.user;

import com.freenow.pojo.user.SingleUser;
import com.freenow.util.PropertyReader;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.freenow.constant.ServiceConstant.BASE_URL;
import static com.freenow.constant.ServiceConstant.USERS_ENDPOINT;
import static io.restassured.RestAssured.when;

public class SingleUserBusinessLogic extends PropertyReader {

    private static final Logger log = LoggerFactory.getLogger(SingleUserBusinessLogic.class);

    /**
     * Extract data as Response object
     * Populate Single User Response POJOs
     * e.g. https://jsonplaceholder.typicode.com/users/1
     *
     * @param id - user id to fetch a single user
     */
    public static SingleUser getSingleUserById(int id) {
        String baseUrl = prop.getProperty(BASE_URL);
        String usersEndpoint = prop.getProperty(USERS_ENDPOINT);
        String url = baseUrl + usersEndpoint + id;
        log.info("API to be hit:" + url);

        Response response = when().get(url);
        SingleUser singleUser = response.getBody().as(SingleUser.class);
        log.info("Info: " + singleUser);
        return singleUser;
    }
}
