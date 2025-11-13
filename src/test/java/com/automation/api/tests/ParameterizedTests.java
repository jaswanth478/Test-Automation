package com.automation.api.tests;

import com.automation.api.BaseTest;
import com.automation.api.models.Post;
import com.automation.api.utils.ApiUtils;
import com.automation.api.utils.AssertionUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParameterizedTests extends BaseTest {

    @DataProvider(name = "postData")
    public Object[][] getPostData() {
        return new Object[][]{
                {1, "First Post", "This is the first test post"},
                {2, "Second Post", "This is the second test post"},
                {3, "Third Post", "This is the third test post"},
                {4, "Fourth Post", "This is the fourth test post"}
        };
    }

    @Test(dataProvider = "postData", groups = {"parameterized"})
    public void testCreateMultiplePosts(int userId, String title, String body) {
        Post post = new Post(userId, title, body);
        
        Response response = ApiUtils.performPost(requestSpec, "/posts", post);

        AssertionUtils.assertStatusCode(response, 201);
        Assert.assertEquals(response.jsonPath().getInt("userId"), userId);
        Assert.assertEquals(response.jsonPath().getString("title"), title);
        Assert.assertEquals(response.jsonPath().getString("body"), body);
    }

    @DataProvider(name = "postIds")
    public Object[] getPostIds() {
        return new Object[]{1, 2, 3, 4, 5};
    }

    @Test(dataProvider = "postIds", groups = {"parameterized"})
    public void testGetMultiplePostsById(int postId) {
        Response response = ApiUtils.performGet(requestSpec, "/posts/" + postId);

        AssertionUtils.assertStatusCode(response, 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), postId);
    }
}

