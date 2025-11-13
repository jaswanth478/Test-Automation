package com.automation.api.tests;

import com.automation.api.BaseTest;
import com.automation.api.utils.ApiUtils;
import com.automation.api.utils.AssertionUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class DeleteTests extends BaseTest {

    @Test(groups = {"smoke", "delete"})
    public void testDeletePost() {
        int postId = 1;
        
        Response response = ApiUtils.performDelete(requestSpec, "/posts/" + postId);

        AssertionUtils.assertStatusCode(response, 200);
    }

    @Test(groups = {"delete"})
    public void testDeleteNonExistentPost() {
        Response response = ApiUtils.performDelete(requestSpec, "/posts/99999");

        AssertionUtils.assertStatusCode(response, 200);
    }
}

