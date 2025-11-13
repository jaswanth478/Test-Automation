package com.automation.api.tests;

import com.automation.api.BaseTest;
import com.automation.api.models.Post;
import com.automation.api.utils.ApiUtils;
import com.automation.api.utils.AssertionUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateTests extends BaseTest {

    @Test(groups = {"smoke", "create"})
    public void testCreatePost() {
        Post newPost = new Post(1, "Test Title", "Test Body Content");
        
        Response response = ApiUtils.performPost(requestSpec, "/posts", newPost);

        AssertionUtils.assertStatusCode(response, 201);
        Assert.assertNotNull(response.jsonPath().getInt("id"));
        Assert.assertEquals(response.jsonPath().getInt("userId"), 1);
        Assert.assertEquals(response.jsonPath().getString("title"), "Test Title");
    }

    @Test(groups = {"create"})
    public void testCreatePostWithEmptyTitle() {
        Post post = new Post(1, "", "Body content");
        
        Response response = ApiUtils.performPost(requestSpec, "/posts", post);

        AssertionUtils.assertStatusCode(response, 201);
    }

    @Test(groups = {"create"})
    public void testCreatePostWithSpecialCharacters() {
        Post post = new Post(1, "Title with @#$%", "Body with special chars !@#$%");
        
        Response response = ApiUtils.performPost(requestSpec, "/posts", post);

        AssertionUtils.assertStatusCode(response, 201);
        Assert.assertEquals(response.jsonPath().getString("title"), "Title with @#$%");
    }
}

