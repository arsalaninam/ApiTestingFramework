package com.freenow.testcase.multipleEndpoints.comments;

import com.freenow.businesslayer.comment.AllCommentsBusinessLogic;
import com.freenow.businesslayer.post.AllPostsBusinessLogic;
import com.freenow.businesslayer.user.SingleUserBusinessLogic;
import com.freenow.data.dataprovider.UserDataProvider;
import com.freenow.pojo.comment.AllComments;
import com.freenow.pojo.comment.SingleComment;
import com.freenow.pojo.post.AllPosts;
import com.freenow.pojo.post.SinglePost;
import com.freenow.pojo.user.SingleUser;
import com.freenow.util.ObjectFactory;
import com.freenow.util.PropertyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static com.freenow.constant.ScenarioNameConstant.VALIDATE_EMAIL_FORMAT_IN_COMMENTS_FOR_POSTS_BY_USER;
import static com.freenow.util.Matcher.validateEmailPattern;

public class CommentsTestUsingMultipleApis extends PropertyReader {

    private static final Logger log = LoggerFactory.getLogger(CommentsTestUsingMultipleApis.class);
    private SoftAssert softAssert = ObjectFactory.getSoftAssert();

    /*************************************************************************************
     * Test Scenario:
     * - Search for the user.
     * - Use the details fetched to make a search for the posts written by the user.
     * - For each post, fetch the comments and validate if the emails in the comment
     * section are in the proper format.
     * @param userName - A valid username e.g."Samantha"
     *************************************************************************************/

    @Test(dataProvider = "validUsername", dataProviderClass = UserDataProvider.class)
    public void validateEmailFormatInCommentsForPostsByUser(String userName) {

        log.info(VALIDATE_EMAIL_FORMAT_IN_COMMENTS_FOR_POSTS_BY_USER);

        SingleUser singleUser = SingleUserBusinessLogic.getSingleUserByUserName(userName);
        log.info("Username : " + userName);
        softAssert.assertNotNull(singleUser);
        int userId = singleUser.getId();
        log.info("User Id: " + userId);

        AllPosts allPostsFromUserId = AllPostsBusinessLogic.getAllPostsForUserId(userId);
        List<SinglePost> allPostsList = allPostsFromUserId.getListOfPosts();
        softAssert.assertNotNull(allPostsList);

        for (int i = 0; i < allPostsList.size(); i++) {
            int postsId = allPostsList.get(i).getId();

            AllComments allCommentsForPostId = AllCommentsBusinessLogic.getAllCommentsForPostId(postsId);
            List<SingleComment> allCommentsList = allCommentsForPostId.getListOfComments();
            softAssert.assertNotNull(allCommentsList);

            for (int j = 0; j < allCommentsList.size(); j++) {

                String email = allCommentsList.get(j).getEmail();
                log.info("Validate Email: " + email);
                softAssert.assertTrue(validateEmailPattern(email));
            }
        }
        softAssert.assertAll();
    }
}
