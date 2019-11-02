package com.freenow.businesslayer.todo;

import com.freenow.pojo.todo.AllTodos;
import com.freenow.pojo.todo.SingleTodo;
import com.freenow.util.PropertyReader;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static com.freenow.constant.ServiceConstant.*;
import static io.restassured.RestAssured.when;

public class AllTodosBusinessLogic extends PropertyReader {

    private static final Logger log = LoggerFactory.getLogger(AllTodosBusinessLogic.class);

    /**
     * Fetch list of all todos
     * e.g. https://jsonplaceholder.typicode.com/todos
     */
    public static AllTodos getAllTodos() {
        String baseUrl = prop.getProperty(BASE_URL);
        String todosEndpoint = prop.getProperty(TODOS_ENDPOINT);
        String url = baseUrl + todosEndpoint;
        log.info("URL to be hit:" + url);

        Response response = when().get(url);
        List<SingleTodo> allSingleTodos = Arrays.asList(response.getBody().as(SingleTodo[].class));

        AllTodos allTodos = new AllTodos();
        allTodos.setListOfTodos(allSingleTodos);
        return allTodos;
    }

    /**
     * Fetch list of all todos json set respective to particular userId
     * e.g. https://jsonplaceholder.typicode.com/todos?userId=1
     */
    public static AllTodos getAllTodosForUserId(String userId) {
        String baseUrl = prop.getProperty(BASE_URL);
        String todosEndpointQueryParamUserId = prop.getProperty(TODOS_ENDPOINT_QUERY_PARAM_USER_ID);
        String url = baseUrl + todosEndpointQueryParamUserId + userId;
        log.info("URL to be hit:" + url);

        Response response = when().get(url);
        List<SingleTodo> allSingleTodos = Arrays.asList(response.getBody().as(SingleTodo[].class));

        AllTodos allTodos = new AllTodos();
        allTodos.setListOfTodos(allSingleTodos);
        return allTodos;
    }
}
