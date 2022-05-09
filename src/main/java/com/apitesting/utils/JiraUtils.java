package com.apitesting.utils;

import com.apitesting.constants.FrameworkConstants;

import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public final class JiraUtils {

    // To avoid external instantiation
    private JiraUtils() {
    }

    /**
     * Create Issue in jira if a test fails
     * Can be configured from the config.properties file
     */
    public static String createIssueInJira(String errorMessage) {

        // We can change these values to any desired set of values for creating defect
        // It needs to be customised for it to be used for enterprise edition
        String postRequestBody = APIUtils
                .readJSONAndReturnString(FrameworkConstants.getJSONRequestFolderPath() + "jiraIssueRequest.json")
                .replace("KEY", "") // change this to your project key
                .replace("SUMMARY", RandomUtils.getRandomText())
                .replace("DESCRIPTION", errorMessage); // Passing the description

        // Change the dummy values to original before use
        Response response = given()
                // Blacklisting the header from being printed to the console
                // Can be useful if someone does not wants to expose their auth keys
                .config(RestAssuredConfig.config().logConfig(LogConfig.logConfig().blacklistHeader("Authorization")))
                // .auth()
                // .basic("Sample_Username", "Sample_Password")
                .header("Authorization", "Basic 6645euchciuc56arcafyecgsaiduci3bgy2y3tfrty") // some random key
                .header("Content-Type", "application/json")
                .log()
                .all()
                .body(postRequestBody)
                .post("/rest/api/2/project");

        response.prettyPrint();
        return response.jsonPath().getString("key");

    }
}
