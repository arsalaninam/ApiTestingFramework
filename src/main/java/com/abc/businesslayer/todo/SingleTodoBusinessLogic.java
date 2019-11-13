package com.abc.businesslayer.todo;

import com.abc.pojo.todo.SingleTodo;
import com.abc.util.PropertyReader;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.abc.constant.ServiceConstant.BASE_URL;
import static com.abc.constant.ServiceConstant.TODOS_ENDPOINT;
import static io.restassured.RestAssured.when;

public class SingleTodoBusinessLogic extends PropertyReader {

    private static final Logger log = LoggerFactory.getLogger(SingleTodoBusinessLogic.class);

    /**
     * Extract data as Response object
     * Populate Single Todos Response POJOs
     * e.g. https://jsonplaceholder.typicode.com/todos/1
     *
     * @param id - todos id to fetch a single todos
     */
    public static SingleTodo getSingleTodoById(int id) {
        String baseUrl = prop.getProperty(BASE_URL);
        String todosEndpoint = prop.getProperty(TODOS_ENDPOINT);
        String url = baseUrl + todosEndpoint + id;
        log.info("URL to be hit : " + url);

        Response response = when().get(url);
        SingleTodo SingleTodo = response.getBody().as(SingleTodo.class);
        log.info("Info : " + SingleTodo);
        return SingleTodo;
    }
}
