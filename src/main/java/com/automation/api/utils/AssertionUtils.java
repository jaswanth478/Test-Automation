package com.automation.api.utils;

import io.restassured.response.Response;
import org.testng.Assert;

public class AssertionUtils {

    public static void assertStatusCode(Response response, int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode,
                "Expected status code " + expectedStatusCode + " but got " + response.getStatusCode());
    }

    public static void assertResponseTime(Response response, long maxTime) {
        long responseTime = response.getTime();
        Assert.assertTrue(responseTime < maxTime,
                "Response time " + responseTime + " exceeds maximum " + maxTime);
    }

    public static void assertContentType(Response response, String expectedContentType) {
        String actualContentType = response.getContentType();
        Assert.assertTrue(actualContentType.contains(expectedContentType),
                "Expected content type " + expectedContentType + " but got " + actualContentType);
    }
}

