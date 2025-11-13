package com.automation.api;

import com.automation.api.config.Config;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    
    protected static RequestSpecification requestSpec;
    
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = Config.BASE_URI;
        
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(RestAssured.baseURI)
                .setContentType(ContentType.JSON)
                .build();
    }
}

