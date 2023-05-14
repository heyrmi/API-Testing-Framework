package com.apitesting.requestbuilder;

import com.apitesting.enums.ConfigProperties;
import com.apitesting.utils.PropertyUtils;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

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
        return given().baseUri(PropertyUtils.getValue(ConfigProperties.BASEURL))
                .config(RestAssured.config().logConfig(LogConfig.logConfig().blacklistHeader("Authorization")))
                .log()
                .ifValidationFails();
    }

    public static RequestSpecification buildRequestForGetCalls(String url) {
        return given().baseUri(url)
                .config(RestAssured.config().logConfig(LogConfig.logConfig().blacklistHeader("Authorization")))
                .log()
                .ifValidationFails();
    }

    public static RequestSpecification buildRequestForPostCalls() {
        return buildRequestForGetCalls()
                .contentType(ContentType.JSON);
    }

    public static RequestSpecification buildRequestForPostCalls(String url) {
        return buildRequestForGetCalls(url)
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
