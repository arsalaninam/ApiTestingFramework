package com.abc.businesslayer.user;

import com.abc.pojo.user.AllUsers;
import com.abc.pojo.user.SingleUser;
import com.abc.util.PropertyReader;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.abc.constant.ServiceConstant.BASE_URL;
import static com.abc.constant.ServiceConstant.USERS_ENDPOINT;
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

    /*
     * Extract data as Response object
     * Populate Single User Response POJOs
     *
     * @param username - username to fetch a single user from users list
     */
    public static SingleUser getSingleUserByUserName(String username) {
        String baseUrl = prop.getProperty(BASE_URL);
        String users = prop.getProperty(USERS_ENDPOINT);
        String url = baseUrl + users;
        log.info("URL to be hit:" + url);

        AllUsers allUsers = AllUsersBusinessLogic.getAllUsers();
        List<SingleUser> allUsersList = allUsers.getListOfUsers();
        SingleUser singleUser = new SingleUser();

        for (int i = 0; i < allUsersList.size(); i++) {
            if (allUsersList.get(i).getUsername().equalsIgnoreCase(username)) {
                singleUser.setId(allUsersList.get(i).getId());
                singleUser.setName(allUsersList.get(i).getName());
                singleUser.setUsername(allUsersList.get(i).getUsername());
                singleUser.setEmail(allUsersList.get(i).getEmail());
                singleUser.setAddress(allUsersList.get(i).getAddress());
                singleUser.setPhone(allUsersList.get(i).getPhone());
                singleUser.setWebsite(allUsersList.get(i).getWebsite());
                singleUser.setCompany(allUsersList.get(i).getCompany());
            }
        }
        return singleUser;
    }
}
