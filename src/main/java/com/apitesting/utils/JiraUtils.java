package com.apitesting.utils;

import com.apitesting.constants.FrameworkConstants;
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
                .replace("DESCRIPTION", errorMessage);  // Passing the description

        //Change the dummy values to original before use
        Response response = given()
                .auth()
                .basic("Sample_Username", "Sample_Password")
                //.header("Authorization", "Some_Random_Key")
                .header("Content-Type", "application/json")
                .log()
                .all()
                .body(postRequestBody)
                .post("/rest/api/2/project");

        response.prettyPrint();
        return response.jsonPath().getString("key");


    }
}
