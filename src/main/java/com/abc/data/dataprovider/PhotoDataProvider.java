package com.abc.data.dataprovider;

import org.testng.annotations.DataProvider;

/**
 * A data provider class to provide test data for photos related test cases
 *
 * @author Arsalan Inam
 */
public class PhotoDataProvider {

    @DataProvider
    public static Object[][] validPhotoIdWithTitleAndUrl() {
        return new Object[][]{
                {1, "accusamus beatae ad facilis cum similique qui sunt", "https://via.placeholder.com/600/92c952"},
                {2, "reprehenderit est deserunt velit ipsam", "https://via.placeholder.com/600/771796"}
        };
    }
}
