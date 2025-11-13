package com.automation.api.tests;

import com.automation.api.BaseTest;
import com.automation.api.models.Post;
import com.automation.api.utils.ApiUtils;
import com.automation.api.utils.AssertionUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IntegrationTests extends BaseTest {

    @Test(groups = {"integration"})
    public void testCreateReadUpdateDeleteFlow() {
        int postId = 1;
        
        Post newPost = new Post(1, "Integration Test Post", "Testing full CRUD flow");
        Response createResponse = ApiUtils.performPost(requestSpec, "/posts", newPost);
        AssertionUtils.assertStatusCode(createResponse, 201);
        Assert.assertNotNull(createResponse.jsonPath().getInt("id"));
        
        Response readResponse = ApiUtils.performGet(requestSpec, "/posts/" + postId);
        AssertionUtils.assertStatusCode(readResponse, 200);
        Assert.assertNotNull(readResponse.jsonPath().getString("title"));
        
        Post updatedPost = new Post(1, "Updated Integration Post", "Updated body");
        Response updateResponse = ApiUtils.performPut(requestSpec, "/posts/" + postId, updatedPost);
        AssertionUtils.assertStatusCode(updateResponse, 200);
        Assert.assertEquals(updateResponse.jsonPath().getString("title"), "Updated Integration Post");
        
        Response deleteResponse = ApiUtils.performDelete(requestSpec, "/posts/" + postId);
        AssertionUtils.assertStatusCode(deleteResponse, 200);
    }
}

