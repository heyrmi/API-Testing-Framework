package com.apitesting.tests;

import com.apitesting.annotation.FrameworkAnnotation;
import com.apitesting.enums.CategoryType;
import com.apitesting.reports.ExtentLogger;
import com.apitesting.requestbuilder.RequestBuilder;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GetTests {


    @Test
    @FrameworkAnnotation(author = {"Lex"}, category = {CategoryType.SMOKE})
    public void getUsersDetails() {
        Response response = RequestBuilder.buildRequestForGetCalls()
                .get("/api/users?page=2");

        response.prettyPrint();

        // to log the response in the extent reports
        ExtentLogger.logResponse(response.asPrettyString());

        System.out.println("\n" + response.getStatusCode());

        //We can use the static imports to reduce verbosity
        assertThat(response.getStatusCode()).isEqualTo(200);
        //Or we can also do normal imports
        Assertions.assertThat(response.jsonPath().getMap("$").size())
                .as("Comparing the size of the map").isEqualTo(6);
    }

    @Test
    @FrameworkAnnotation(author = {"Joe"}, category = {CategoryType.SMOKE})
    public void getUserDetails() {
        Response response = RequestBuilder.buildRequestForGetCalls()
                .pathParams("id", 2)
                .get("/api/users/{id}");

        response.prettyPrint();

        // to log the response in the extent reports
        ExtentLogger.logResponse(response.asPrettyString());

        System.out.println("\n" + response.getStatusCode());

        //We can use the static imports to reduce verbosity
        assertThat(response.getStatusCode()).isEqualTo(200);
        //Or we can also do normal imports
        Assertions.assertThat(response.jsonPath().getMap("$").size())
                .as("Comparing the size of the map").isEqualTo(2);

        assertThat((String) response.jsonPath().get("data.last_name"))
                .as("Comparing name to its case").isEqualTo("Weaver");

    }
}
