package com.automation.api.utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    public static Response performGet(RequestSpecification spec, String endpoint) {
        return given()
                .spec(spec)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    public static Response performPost(RequestSpecification spec, String endpoint, Object body) {
        return given()
                .spec(spec)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    public static Response performPut(RequestSpecification spec, String endpoint, Object body) {
        return given()
                .spec(spec)
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    public static Response performPatch(RequestSpecification spec, String endpoint, Object body) {
        return given()
                .spec(spec)
                .body(body)
                .when()
                .patch(endpoint)
                .then()
                .extract()
                .response();
    }

    public static Response performDelete(RequestSpecification spec, String endpoint) {
        return given()
                .spec(spec)
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }
}

