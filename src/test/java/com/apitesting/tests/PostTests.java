package com.apitesting.tests;

import com.apitesting.annotation.FrameworkAnnotation;
import com.apitesting.constants.FrameworkConstants;
import com.apitesting.constants.FrameworkConstantsWithSingleton;
import com.apitesting.enums.CategoryType;
import com.apitesting.pojo.User;
import com.apitesting.reports.ExtentLogger;
import com.apitesting.requestbuilder.RequestBuilder;
import com.apitesting.utils.APIUtils;
import com.apitesting.utils.RandomUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.apitesting.utils.RandomUtils.getRandomFirstName;
import static com.apitesting.utils.RandomUtils.getRandomJobTitle;

public class PostTests {

    @Test
    @FrameworkAnnotation(author = {"Zeta"}, category = {CategoryType.STORYTESTING})
    public void postUsers() {
        // TODO: Make method for post multiple users
        // Can't be made since ReqRes.in doesn't support this request
        // Adding fake assertion to fail this test
        Assert.assertTrue(false);
    }

    @Test
    @FrameworkAnnotation(author = {"Alex"}, category = {CategoryType.STORYTESTING})
    public void postUserForSkip() {
        throw new SkipException("Data not available");
    }

    @Test
    @FrameworkAnnotation(author = {"Zarvis"}, category = {CategoryType.SMOKE})
    public void postUser() {

        // Create a post call with new user and create body using POJO
        /*
         * {
         * "name": "morpheus",
         * "job": "leader"
         * }
         */

        // using fake data here
        User user = User
                .builder()
                .setName(getRandomFirstName())
                .setJobTitle(getRandomJobTitle())
                .build();

        RequestSpecification requestSpecification = RequestBuilder
                .buildRequestForPostCalls()
                .body(user);

        // Logging the request
        ExtentLogger.logRequest(requestSpecification);

        Response response = requestSpecification.post("/api/users");

        Assertions.assertThat(response.getStatusCode()).isEqualTo(201);

    }

    @Test
    @FrameworkAnnotation(author = {"Rahul"}, category = {CategoryType.MINIREGRESSION})
    public void postRequestUsingExternalFile(Method method) {
        // using simple JSON, can also manage Post Request with complex JSON body
        // autofilling the random data before every function call
        String postRequestBody = APIUtils
                .readJSONAndReturnString(
                        FrameworkConstants.getJSONRequestFolderPath() + "request.json")
                .replace("fname", RandomUtils.getRandomFirstName())
                .replace("givenJob", RandomUtils.getRandomJobTitle());

        // Doing this for logging the request and response
        RequestSpecification requestSpecification = RequestBuilder
                .buildRequestForPostCalls()
                .body(postRequestBody);

        // Logging the request
        ExtentLogger.logRequest(requestSpecification);

        Response response = requestSpecification.post("/api/users");

        APIUtils.storeStringAsJSONFile(
                FrameworkConstants.getJSONResponseFolderPath() + method.getName() + "Response.json",
                response);

        // to log the response in the extent reports
        ExtentLogger.logResponse(response.asPrettyString());

        Assertions.assertThat(response.getStatusCode()).isEqualTo(201);
    }

    @Test
    @FrameworkAnnotation(author = {"Rahul"}, category = {CategoryType.REGRESSION})
    public void postRequestUsingExternalFileUsingSingleton(Method method) {
        // using simple JSON, can also manage Post Request with complex JSON body
        // autofilling the random data before every function call
        String postRequestBody = APIUtils
                .readJSONAndReturnString(
                        FrameworkConstantsWithSingleton.getInstance().getJSONRequestFolderPath()
                                + "request.json")
                .replace("fname", RandomUtils.getRandomFirstName())
                .replace("givenJob", RandomUtils.getRandomJobTitle());

        // Doing this for logging the request and response
        RequestSpecification requestSpecification = RequestBuilder
                .buildRequestForPostCalls()
                .body(postRequestBody);

        // Logging the request
        ExtentLogger.logRequest(requestSpecification);

        Response response = requestSpecification.post("/api/users");

        APIUtils.storeStringAsJSONFile(
                FrameworkConstantsWithSingleton.getInstance().getJSONResponseFolderPath()
                        + method.getName() + "Response.json",
                response);

        // to log the response in the extent reports
        ExtentLogger.logResponse(response.asPrettyString());

        Assertions.assertThat(response.getStatusCode()).isEqualTo(201);
    }
}
