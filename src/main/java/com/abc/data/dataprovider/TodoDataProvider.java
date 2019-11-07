package com.freenow.data.dataprovider;

import org.testng.annotations.DataProvider;

/**
 * A data provider class to provide test data for todos related test cases
 *
 * @author Arsalan Inam
 */
public class TodoDataProvider {

    @DataProvider
    public static Object[][] validTodosIdWithTitleAndStatus() {
        return new Object[][]{
                {1, "delectus aut autem", false},
                {2, "quis ut nam facilis et officia qui", false}
        };
    }
}
