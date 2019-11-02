package com.freenow.testcase.singleEndpoint.todo;

import com.freenow.businesslayer.todo.SingleTodoBusinessLogic;
import com.freenow.data.dataprovider.CommonDataProvider;
import com.freenow.data.dataprovider.TodoDataProvider;
import com.freenow.pojo.todo.SingleTodo;
import com.freenow.testcase.singleEndpoint.SingleEndpointCommon;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.freenow.constant.ResponseCodeConstant.STATUS_CODE_404;
import static com.freenow.constant.ScenarioNameConstant.*;
import static com.freenow.constant.ServiceConstant.TODOS_ENDPOINT;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

public class TodosTest extends SingleEndpointCommon {

    private static final Logger log = LoggerFactory.getLogger(TodosTest.class);
    private String todosEndpoint = prop.getProperty(TODOS_ENDPOINT);

    /**********************************************************
     * Send a GET request to /todos. Validate that response
     * has HTTP status code 200 and Content Type JSON
     *********************************************************/
    @Test
    public void testResponseStatusCode200AndContentTypeJSON() {
        log.info(VALIDATE_STATUS_CODE_200_AND_CONTENT_TYPE_JSON + todosEndpoint);
        given().
                spec(requestSpecification).
                when().
                get(todosEndpoint).
                then().
                spec(responseSpecification);
    }

    /***********************************************************
     * Send a GET request to /todos. Validate that
     * - response body contains list of 200 todos
     * - response has HTTP status code 200 and Content Type JSON
     ***********************************************************/
    @Test(dependsOnMethods = "testResponseStatusCode200AndContentTypeJSON")
    public void fetchListOfTodosAndAssertSize() {
        log.info(VALIDATE_LIST_OF_ITEM + todosEndpoint);
        given().
                spec(requestSpecification).
                when().
                get(todosEndpoint).
                then().
                assertThat().
                body("$", hasSize(200)).
                and().
                spec(responseSpecification);
    }

    /************************************************************
     * Send a GET request to /todos/{todosId} and Validate
     * - response has HTTP status code 200 & Content Type is JSON
     ***********************************************************/
    @Test(dataProvider = "validId", dataProviderClass = CommonDataProvider.class)
    public void testResponseCodeAndContentType(int id) {
        log.info(VALIDATE_STATUS_CODE_200_AND_CONTENT_TYPE_JSON + todosEndpoint + id);
        given().
                spec(requestSpecification).
                when().
                get(todosEndpoint + id).
                then().
                spec(responseSpecification);
    }

    /********************************************************
     * Send a GET request to /todos/{todosId} and Validate
     * - response returns the expected id, title & status
     ********************************************************/
    @Test(dependsOnMethods = {"testResponseCodeAndContentType"},
            dataProvider = "validTodosIdWithTitleAndStatus", dataProviderClass = TodoDataProvider.class)
    public void testResponseBodyWithIdTitleAndStatus(int id, String title, boolean status) {
        log.info(VALIDATE_RESPONSE_BODY + todosEndpoint + id);

        SingleTodo singleTodo = SingleTodoBusinessLogic.getSingleTodoById(id);
        softAssert.assertEquals(singleTodo.getId(), id);
        softAssert.assertEquals(singleTodo.getTitle(), title);
        softAssert.assertEquals(singleTodo.getCompleted(), String.valueOf(status));
        softAssert.assertAll();
    }

    /***************************************************************
     * Send a GET request to /todos/{todosId} with invalid todosId
     * Validate that response has HTTP status code 404
     **************************************************************/
    @Test(dataProvider = "invalidId", dataProviderClass = CommonDataProvider.class)
    public void testResponseCodeWithInvalidTodosId(int id) {
        log.info(VALIDATE_STATUS_CODE_404 + todosEndpoint + id);
        Response response = given().
                spec(requestSpecification).
                when().
                get(todosEndpoint + id);
        Assert.assertEquals(response.statusCode(), STATUS_CODE_404);
    }

}
