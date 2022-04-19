package com.apitesting.utils;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public final class ApiUtils {
    //to avoid external instantiation
    private ApiUtils(){}

    public static RequestSpecification buildRequestForGetCalls(){
        return given().baseUri("https://reqres.in")
                .log()
                .all();
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
