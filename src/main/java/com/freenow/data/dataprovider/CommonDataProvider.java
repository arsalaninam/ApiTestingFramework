package com.freenow.data.dataprovider;

import org.testng.annotations.DataProvider;

/**
 * A common data provider class to provide similar test data
 *
 * @author Arsalan Inam
 */
public class CommonDataProvider {

    @DataProvider
    public static Object[][] validId() {
        return new Object[][]{
                {1}, {2}
        };
    }

    @DataProvider
    public static Object[][] invalidId() {
        return new Object[][]{
                {7000}, {8000}
        };
    }
}
