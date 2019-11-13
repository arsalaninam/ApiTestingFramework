package com.abc.data.dataprovider;

import org.testng.annotations.DataProvider;

/**
 * A common data provider class to provide test data used in multiple test cases
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

    @DataProvider
    public static Object[][] validUsernameWithNumberOfAlbums() {
        return new Object[][]{
                {"Bret", 10}, {"Samantha", 10}, {"Maxime_Nienow", 10}
        };
    }

    @DataProvider
    public static Object[][] validUsernameWithNumberOfPosts() {
        return new Object[][]{
                {"Antonette", 10}, {"Karianne", 10}, {"Kamren", 10}
        };
    }

    @DataProvider
    public static Object[][] validUsernameWithNumberOfTodos() {
        return new Object[][]{
                {"Leopoldo_Corkery", 20}, {"Elwyn.Skiles", 20}, {"Moriah.Stanton", 20}
        };
    }
}