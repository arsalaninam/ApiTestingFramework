package com.abc.data.dataprovider;

import org.testng.annotations.DataProvider;

/**
 * A data provider class to provide test data for albums related test cases
 *
 * @author Arsalan Inam
 */
public class AlbumDataProvider {

    @DataProvider
    public static Object[][] validAlbumIdWithTitle() {
        return new Object[][]{
                {1, "quidem molestiae enim"},
                {2, "sunt qui excepturi placeat culpa"}
        };
    }
}
