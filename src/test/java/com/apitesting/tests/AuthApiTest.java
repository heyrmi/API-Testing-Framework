package com.apitesting.tests;

import org.testng.annotations.Test;
import com.apitesting.annotation.TestDetails;
import com.apitesting.constants.FrameworkConstants;
import com.apitesting.enums.CategoryType;
import com.apitesting.reports.ExtentLogger;
import com.apitesting.requestbuilder.RequestBuilder;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

/**
 * This class will Test Github's protected APIs.
 */
public class AuthApiTest {

        // To replace blocking user dynamically
        private static String blockUserName = "errfree";
        private static String blockUserBasePath = String.format("/user/blocks/%s", blockUserName);

        // Repository name
        private static String repoName = "hiworld";

        // Bearer Token (getting it from GithubActions)
        private static String personalAccessToken = System.getenv("ACCESSTOKEN");
        private static String bearerToken = String.format("Bearer %s", personalAccessToken);

        /**
         * This method will test GitHub's users repository: Get Request
         */
        @Test(enabled = true)
        @TestDetails(author = { "Rahul Mishra" }, category = CategoryType.REGRESSION)
        public void testNumberOfGithubRepoOfUser() {

                RequestSpecification requestSpecification = RequestBuilder
                                .buildRequestForGetCalls(FrameworkConstants.getGITHUB_BASE_URI())
                                .header("X-GitHub-Api-Version", "2022-11-28");

                Response response = requestSpecification.get("/users/heyrmi/repos");

                response.prettyPrint();

                ExtentLogger.logRequest(requestSpecification);
                ExtentLogger.logResponse(response.asPrettyString());
                ExtentLogger.info("Response status code: " + response.statusCode());

                String stringReponse = response.asPrettyString();
                JsonPath jsonPath = new JsonPath(stringReponse);

                // Get first repository name from JSON response
                String name = jsonPath.getString("[0].name");

                // Console output the name of first repo
                System.out.println("name = " + name);

                // Asserting with both (restAssured and assertJ)
                response.then().statusCode(200);
                assertThat(response.statusCode()).isEqualTo(200);
                assertThat(name).isNotBlank().isNotEmpty().containsIgnoringCase("API-Testing-Framework");
        }

        /**
         * This method will test Github Repo API: Post Request
         */
        @Test
        @TestDetails(author = { "Rahul Mishra" }, category = CategoryType.REGRESSION)
        public void testCreateGithubRepoAPI() {

                // Setting the request body as hashmap
                Map<String, Object> requestBody = new HashMap<>();
                requestBody.put("name", repoName);
                requestBody.put("description", "This repo was created by Rest Assured Test!");
                requestBody.put("homepage", "https://github.com");
                requestBody.put("private", false);
                requestBody.put("is_template", false);

                RequestSpecification requestSpecification = RequestBuilder
                                .buildRequestForPostCalls(FrameworkConstants.getGITHUB_BASE_URI())
                                .header("Accept", "application/vnd.github+json")
                                .header("X-GitHub-Api-Version", "2022-11-28")
                                .header("Authorization", bearerToken)
                                .body(requestBody);

                Response response = requestSpecification.post("/user/repos");

                // Log Response
                response.prettyPrint();
                ExtentLogger.logRequest(requestSpecification);
                ExtentLogger.logResponse(response.asPrettyString());
                ExtentLogger.info("Response status code: " + response.statusCode());

                // Pass Response to JsonPath
                String stringReponse = response.asPrettyString();
                JsonPath jsonPath = new JsonPath(stringReponse);

                // Get full_name from JSON response
                String fullName = jsonPath.getString("full_name");

                // Console output the name of first repo
                System.out.println("name = " + fullName);

                response.then().statusCode(201);
                assertThat(response.statusCode()).isEqualTo(201);

                assertThat(fullName)
                                .isNotEmpty()
                                .isNotBlank()
                                .isEqualToIgnoringCase(String.format("heyrmi/%s", repoName));

        }

        /**
         * This method will test Github User Block API: Put Request
         */
        @Test(enabled = true)
        @TestDetails(author = { "Rahul Mishra" }, category = CategoryType.REGRESSION)
        public void testGithubUserBlock() {
                RequestSpecification requestSpecification = RequestBuilder
                                .buildRequestForPostCalls(FrameworkConstants.getGITHUB_BASE_URI())
                                .header("Accept", "application/vnd.github+json")
                                .header("X-GitHub-Api-Version", "2022-11-28")
                                .header("Authorization", bearerToken);

                Response response = requestSpecification.put(blockUserBasePath);

                // Log Response
                response.prettyPrint();
                ExtentLogger.logRequest(requestSpecification);
                ExtentLogger.logResponse(response.asPrettyString());
                ExtentLogger.info("Response status code: " + response.statusCode());

                // Asserting with both (restAssured and assertJ)
                response.then().statusCode(204);
                assertThat(response.statusCode()).isEqualTo(204);

        }

        // Only unblock once the user is blocked
        /**
         * This method will test Github User Unblock API: Delete Request
         */
        @Test(dependsOnMethods = { "testGithubUserBlock" }, enabled = true)
        @TestDetails(author = { "Rahul Mishra" }, category = CategoryType.REGRESSION)
        public void testGithubUserUnblock() {
                RequestSpecification requestSpecification = RequestBuilder
                                .buildRequestForPostCalls(FrameworkConstants.getGITHUB_BASE_URI())
                                .header("Accept", "application/vnd.github+json")
                                .header("X-GitHub-Api-Version", "2022-11-28")
                                .header("Authorization", bearerToken);

                Response response = requestSpecification.delete(blockUserBasePath);

                // Log Response
                response.prettyPrint();
                ExtentLogger.logRequest(requestSpecification);
                ExtentLogger.logResponse(response.asPrettyString());
                ExtentLogger.info("Response status code: " + response.statusCode());

                // Asserting with restAssured and assertJ
                response.then().statusCode(204);
                assertThat(response.statusCode()).isEqualTo(204);
        }
}
