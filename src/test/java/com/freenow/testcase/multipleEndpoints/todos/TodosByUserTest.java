package com.freenow.testcase.multipleEndpoints.todos;

import com.freenow.businesslayer.todo.AllTodosBusinessLogic;
import com.freenow.businesslayer.user.SingleUserBusinessLogic;
import com.freenow.data.dataprovider.CommonDataProvider;
import com.freenow.pojo.todo.AllTodos;
import com.freenow.pojo.todo.SingleTodo;
import com.freenow.pojo.user.SingleUser;
import com.freenow.testcase.multipleEndpoints.posts.PostsByUserTest;
import com.freenow.util.ObjectFactory;
import com.freenow.util.PropertyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static com.freenow.constant.ScenarioNameConstant.VALIDATE_TOTAL_NUMBER_OF_TODOS_ASSOCIATED_WITH_USER;

/**
 * Test Class to perform all the Todos related test cases that are associated with a User
 * Endpoints that are used for Todos test cases in the following Test Class are:
 * - https://<domain>/users
 * - https://<domain>/todos?userId={userId}
 *
 * @author Arsalan Inam
 */

public class TodosByUserTest extends PropertyReader {

    private static final Logger log = LoggerFactory.getLogger(PostsByUserTest.class);
    private SoftAssert softAssert = ObjectFactory.getSoftAssert();

    /*************************************************************************************
     * Test Scenario:
     * - Search for the user.
     * - Use the details fetched to make a search for the todos associated with user.
     * - Validate total number of todos associated with user,
     * - Validate response body of todos associated with user contains userId
     * - Validate response body of todos associated with user is not null
     *
     * @param username - A valid username e.g. "Bret", "Samantha" etc.
     * @param numberOfTodos - Total number of todos associated with a user
     *************************************************************************************/

    @Test(dataProvider = "validUsernameWithNumberOfTodos", dataProviderClass = CommonDataProvider.class)
    public void testTotalCountOfTodosAssociatedWithUserAndValidateResponseBody(String username, int numberOfTodos) {
        log.info(VALIDATE_TOTAL_NUMBER_OF_TODOS_ASSOCIATED_WITH_USER);

        SingleUser singleUser = SingleUserBusinessLogic.getSingleUserByUserName(username);
        log.info("Username : " + username);
        softAssert.assertNotNull(singleUser);
        int userId = singleUser.getId();
        log.info("User Id: " + userId);

        AllTodos allTodosFromUserId = AllTodosBusinessLogic.getAllTodosForUserId(userId);
        List<SingleTodo> allTodosList = allTodosFromUserId.getListOfTodos();
        softAssert.assertEquals(allTodosList.size(), numberOfTodos, "Total number of todos associated with user");
        log.info("Total Number of Todos : " + allTodosList.size() + " associated with User : " + username);

        for (SingleTodo singleTodo : allTodosList) {
            softAssert.assertEquals(singleTodo.getUserId(), userId);
            softAssert.assertNotNull(singleTodo.getTitle());
            softAssert.assertNotNull(singleTodo.getCompleted());
        }
        softAssert.assertAll();
    }
}
