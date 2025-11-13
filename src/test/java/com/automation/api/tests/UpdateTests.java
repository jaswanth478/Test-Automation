package com.automation.api.tests;

import com.automation.api.BaseTest;
import com.automation.api.models.Post;
import com.automation.api.utils.ApiUtils;
import com.automation.api.utils.AssertionUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateTests extends BaseTest {

    @Test(groups = {"smoke", "update"})
    public void testUpdatePost() {
        int postId = 1;
        Post updatedPost = new Post(2, "Updated Title", "Updated Body");
        
        Response response = ApiUtils.performPut(requestSpec, "/posts/" + postId, updatedPost);

        AssertionUtils.assertStatusCode(response, 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), postId);
        Assert.assertEquals(response.jsonPath().getString("title"), "Updated Title");
        Assert.assertEquals(response.jsonPath().getString("body"), "Updated Body");
    }

    @Test(groups = {"update"})
    public void testPartialUpdatePost() {
        int postId = 1;
        String partialUpdate = "{\"title\": \"Partially Updated Title\"}";
        
        Response response = ApiUtils.performPatch(requestSpec, "/posts/" + postId, partialUpdate);

        AssertionUtils.assertStatusCode(response, 200);
        Assert.assertEquals(response.jsonPath().getString("title"), "Partially Updated Title");
    }
}

