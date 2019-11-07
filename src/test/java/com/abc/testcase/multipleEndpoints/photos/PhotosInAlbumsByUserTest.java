package com.abc.testcase.multipleEndpoints.photos;

import com.abc.businesslayer.album.AllAlbumsBusinessLogic;
import com.abc.businesslayer.photo.AllPhotosBusinessLogic;
import com.abc.businesslayer.user.SingleUserBusinessLogic;
import com.abc.data.dataprovider.UserDataProvider;
import com.abc.pojo.album.AllAlbums;
import com.abc.pojo.album.SingleAlbum;
import com.abc.pojo.photo.AllPhotos;
import com.abc.pojo.photo.SinglePhoto;
import com.abc.pojo.user.SingleUser;
import com.abc.util.ObjectFactory;
import com.abc.util.PropertyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static com.abc.constant.ScenarioNameConstant.VALIDATE_URL_FORMAT_IN_PHOTOS_FOR_ALBUMS_ASSOCIATED_WITH_USER;
import static com.abc.util.Matcher.isValidURL;

/**
 * Test Class to perform all the Photos related test cases that are in Albums associated with a User
 * Endpoints that are used for Photos test cases in the following Test Class are:
 * - https://<domain>/users
 * - https://<domain>/albums?userId={userId}
 * - https://<domain>/photos?albumId={albumId}
 *
 * @author Arsalan Inam
 */

public class PhotosInAlbumsByUserTest extends PropertyReader {

    private static final Logger log = LoggerFactory.getLogger(PhotosInAlbumsByUserTest.class);
    private SoftAssert softAssert = ObjectFactory.getSoftAssert();

    /*************************************************************************************
     * Test Scenario:
     * - Search for the user.
     * - Use the details fetched to make a search for the albums associated with a user.
     * - For each albums, fetch the photos and validate if the url and thumbnailUrl in the
     * photos section are in the proper url format.
     *
     * @param username - A valid username e.g."Samantha"
     *************************************************************************************/

    @Test(dataProvider = "validUsername", dataProviderClass = UserDataProvider.class)
    public void validateUrlFormatInPhotosForAlbumsAssociatedWithUser(String username) {
        log.info(VALIDATE_URL_FORMAT_IN_PHOTOS_FOR_ALBUMS_ASSOCIATED_WITH_USER);

        SingleUser singleUser = SingleUserBusinessLogic.getSingleUserByUserName(username);
        log.info("Username : " + username);
        softAssert.assertNotNull(singleUser);
        int userId = singleUser.getId();
        log.info("User Id: " + userId);

        AllAlbums allAlbumsFromUserId = AllAlbumsBusinessLogic.getAllAlbumsForUserId(userId);
        List<SingleAlbum> allAlbumsList = allAlbumsFromUserId.getListOfAlbums();
        softAssert.assertNotNull(allAlbumsList);

        for (SingleAlbum singleAlbum : allAlbumsList) {
            int albumId = singleAlbum.getId();

            AllPhotos allPhotosForAlbumId = AllPhotosBusinessLogic.getAllPhotosForAlbumId(albumId);
            List<SinglePhoto> allPhotosList = allPhotosForAlbumId.getListOfPhotos();
            softAssert.assertNotNull(allPhotosList);

            for (SinglePhoto singlePhoto : allPhotosList) {

                String url = singlePhoto.getUrl();
                log.info("Validate Url: " + url);
                softAssert.assertTrue(isValidURL(url));
                String thumbnailUrl = singlePhoto.getThumbnailUrl();
                log.info("Validate Thumbnail Url: " + thumbnailUrl);
                softAssert.assertTrue(isValidURL(thumbnailUrl));

            }
        }
        softAssert.assertAll();
    }
}
