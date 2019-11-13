package com.abc.testcase.multipleEndpoints.albums;

import com.abc.businesslayer.album.AllAlbumsBusinessLogic;
import com.abc.businesslayer.user.SingleUserBusinessLogic;
import com.abc.data.dataprovider.CommonDataProvider;
import com.abc.pojo.album.AllAlbums;
import com.abc.pojo.album.SingleAlbum;
import com.abc.pojo.user.SingleUser;
import com.abc.util.ObjectFactory;
import com.abc.util.PropertyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static com.abc.constant.ScenarioNameConstant.VALIDATE_TOTAL_NUMBER_OF_ALBUMS_ASSOCIATED_WITH_USER;

/**
 * Test Class to perform all the Albums related test cases that are associated with a User
 * Endpoints that are used for Albums test cases in the following Test Class are:
 * - https://<domain>/users
 * - https://<domain>/albums?userId={userId}
 *
 * @author Arsalan Inam
 */

public class AlbumsOfUserTest extends PropertyReader {

    private static final Logger log = LoggerFactory.getLogger(AlbumsOfUserTest.class);
    private SoftAssert softAssert = ObjectFactory.getSoftAssert();

    /*************************************************************************************
     * Test Scenario:
     * - Search for the user.
     * - Use the details fetched to make a search for the albums associated with user.
     * - Validate total number of albums associated with a user,
     * - Validate response body of albums contains relative userId
     * - Validate response body of albums is not null
     *
     * @param username - A valid username e.g. "Bret", "Samantha" etc.
     * @param numberOfAlbums - Total number of albums associated with a user
     *************************************************************************************/

    @Test(dataProvider = "validUsernameWithNumberOfAlbums", dataProviderClass = CommonDataProvider.class)
    public void testTotalCountOfAlbumsAssociatedWithUserAndValidateResponseBody(String username, int numberOfAlbums) {
        log.info(VALIDATE_TOTAL_NUMBER_OF_ALBUMS_ASSOCIATED_WITH_USER);

        SingleUser singleUser = SingleUserBusinessLogic.getSingleUserByUserName(username);
        log.info("Username : " + username);
        Assert.assertNotNull(singleUser);
        int userId = singleUser.getId();
        log.info("User Id: " + userId);

        AllAlbums allAlbumsFromUserId = AllAlbumsBusinessLogic.getAllAlbumsForUserId(userId);
        List<SingleAlbum> allAlbumsList = allAlbumsFromUserId.getListOfAlbums();
        softAssert.assertEquals(allAlbumsList.size(), numberOfAlbums, "Total number of albums by user");
        log.info("Total Number of Albums : " + allAlbumsList.size() + " Associated With User : " + username);

        for (SingleAlbum singleAlbum : allAlbumsList) {
            softAssert.assertEquals(singleAlbum.getUserId(), userId);
            softAssert.assertNotNull(singleAlbum.getTitle());
        }
        softAssert.assertAll();
    }
}
