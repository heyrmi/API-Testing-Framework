package com.apitesting.requestbuilder;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public final class RequestBuilder {
    // to avoid external instantiation
    private RequestBuilder() {
    }

    /**
     * Build Request For Get Calls
     * 
     * @return Request Specification
     */
    public static RequestSpecification buildRequestForGetCalls() {
        return given().baseUri("https://reqres.in")
                .log()
                .all();
    }

    public static RequestSpecification buildRequestForPostCalls() {
        return buildRequestForGetCalls()
                .contentType(ContentType.JSON);
    }

    /**
     * JsonPath evaluator
     *
     * @param response response
     * @return jsonPath
     */
    protected Object jsonPathEvaluator(Response response, String exp) {
        return response.jsonPath().get(exp);
    }

}
