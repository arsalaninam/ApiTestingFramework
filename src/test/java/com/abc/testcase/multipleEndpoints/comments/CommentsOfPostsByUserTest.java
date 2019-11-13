package com.abc.testcase.multipleEndpoints.comments;

import com.abc.businesslayer.comment.AllCommentsBusinessLogic;
import com.abc.businesslayer.post.AllPostsBusinessLogic;
import com.abc.businesslayer.user.SingleUserBusinessLogic;
import com.abc.data.dataprovider.UserDataProvider;
import com.abc.pojo.comment.AllComments;
import com.abc.pojo.comment.SingleComment;
import com.abc.pojo.post.AllPosts;
import com.abc.pojo.post.SinglePost;
import com.abc.pojo.user.SingleUser;
import com.abc.util.ObjectFactory;
import com.abc.util.PropertyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static com.abc.constant.ScenarioNameConstant.VALIDATE_EMAIL_FORMAT_IN_COMMENTS_FOR_POSTS_BY_USER;
import static com.abc.util.Matcher.validateEmailPattern;

/**
 * Test Class to perform all the Comments related test cases that are present in Posts made by a User
 * Endpoints that are used for Comments test cases in the following Test Class are:
 * - https://<domain>/users
 * - https://<domain>/posts?userId={userId}
 * - https://<domain>/comments?postId={postId}
 *
 * @author Arsalan Inam
 */

public class CommentsOfPostsByUserTest extends PropertyReader {

    private static final Logger log = LoggerFactory.getLogger(CommentsOfPostsByUserTest.class);
    private SoftAssert softAssert = ObjectFactory.getSoftAssert();

    /*************************************************************************************
     * Test Scenario:
     * - Search for the user.
     * - Use the details fetched to make a search for the posts written by the user.
     * - For each post, fetch the comments and validate if the emails in the comment
     * section are in the proper format.
     *
     * @param userName - A valid username e.g."Samantha"
     *************************************************************************************/

    @Test(dataProvider = "validUsername", dataProviderClass = UserDataProvider.class)
    public void validateEmailFormatInCommentsForPostsByUser(String userName) {

        log.info(VALIDATE_EMAIL_FORMAT_IN_COMMENTS_FOR_POSTS_BY_USER);

        SingleUser singleUser = SingleUserBusinessLogic.getSingleUserByUserName(userName);
        log.info("Username : " + userName);
        Assert.assertNotNull(singleUser);
        int userId = singleUser.getId();
        log.info("User Id: " + userId);

        AllPosts allPostsFromUserId = AllPostsBusinessLogic.getAllPostsForUserId(userId);
        List<SinglePost> allPostsList = allPostsFromUserId.getListOfPosts();
        Assert.assertNotNull(allPostsList);

        for (SinglePost singlePost : allPostsList) {
            int postsId = singlePost.getId();

            AllComments allCommentsForPostId = AllCommentsBusinessLogic.getAllCommentsForPostId(postsId);
            List<SingleComment> allCommentsList = allCommentsForPostId.getListOfComments();
            Assert.assertNotNull(allCommentsList);

            for (SingleComment singleComment : allCommentsList) {

                String email = singleComment.getEmail();
                log.info("Validate Email: " + email);
                softAssert.assertTrue(validateEmailPattern(email));
            }
        }
        softAssert.assertAll();
    }
}
