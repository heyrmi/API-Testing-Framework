package com.gatling.tests;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import java.util.HashMap;
import java.util.Map;

public class TwitterApiTest extends Simulation {

    // Bearer Token (getting it from GithubActions)
    private static String twitterAccessToken = System.getenv("TWITTER_ACCESS_TOKEN");
    private static String bearerToken = String.format("Bearer %s", twitterAccessToken);

    // To replace tweet ID dynamically
    private static String tweetId = "1278347468690915330";
    private static String getTweetUri = String.format("/tweets?ids=%s", tweetId);

    // 1. Http Configuration
    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("https://api.twitter.com/2")
            .acceptHeader("application/json");

    // 2. Scenario Builder (Reading a twitter tweet using auth)
    private ScenarioBuilder scenarioBuilder = scenario("Twitter API Test")
            .exec(http("Get Tweet from ID")
                    .get(getTweetUri)
                    .header("Authorization", bearerToken)
                    .check(status().is(200)))
            .pause(1);

    // 3. Load simulation
    {
        setUp(scenarioBuilder.injectOpen(atOnceUsers(2))).protocols(httpProtocol);
    }

}
