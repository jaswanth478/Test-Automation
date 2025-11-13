package com.automation.api.tests;

import com.automation.api.BaseTest;
import com.automation.api.utils.ApiUtils;
import com.automation.api.utils.AssertionUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ReadTests extends BaseTest {

    @Test(groups = {"smoke", "read"})
    public void testGetAllPosts() {
        Response response = ApiUtils.performGet(requestSpec, "/posts");

        AssertionUtils.assertStatusCode(response, 200);
        AssertionUtils.assertContentType(response, "json");
        Assert.assertTrue(response.jsonPath().getList("").size() > 0);
    }

    @Test(groups = {"smoke", "read"})
    public void testGetPostById() {
        int postId = 1;
        
        Response response = ApiUtils.performGet(requestSpec, "/posts/" + postId);

        AssertionUtils.assertStatusCode(response, 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), postId);
        Assert.assertNotNull(response.jsonPath().getString("title"));
        Assert.assertNotNull(response.jsonPath().getString("body"));
    }

    @Test(groups = {"read"})
    public void testGetPostByUserId() {
        int userId = 1;
        
        Response response = given()
                .spec(requestSpec)
                .queryParam("userId", userId)
                .when()
                .get("/posts")
                .then()
                .extract()
                .response();

        AssertionUtils.assertStatusCode(response, 200);
        Assert.assertTrue(response.jsonPath().getList("").size() > 0);
    }

    @Test(groups = {"read"})
    public void testGetNonExistentPost() {
        Response response = ApiUtils.performGet(requestSpec, "/posts/99999");

        AssertionUtils.assertStatusCode(response, 404);
    }

    @Test(groups = {"read"}, dependsOnMethods = {"testGetPostById"})
    public void testGetPostResponseTime() {
        Response response = ApiUtils.performGet(requestSpec, "/posts/1");
        
        AssertionUtils.assertStatusCode(response, 200);
        AssertionUtils.assertResponseTime(response, 3000);
    }
}

