package com.gatling.tests;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class TwitterApiPostTest extends Simulation {

    // Consumer Key, Access Token, oAuthSignature (getting it from GithubActions)
    private static String consumerKey = System.getenv("TWITTER_CONSUMER_KEY");
    private static String oAuthSig = System.getenv("TWITTER_SIG");
    private static String accessToken = System.getenv("TWITTER_TOKEN");

    private static String oAuth = String.format(
            "OAuth oauth_consumer_key=%s,oauth_token=%s, oauth_signature_method=HMAC-SHA1, oauth_timestamp=1685022499, oauth_nonce=PKoJ3F5rZFX, oauth_version=1.0, oauth_signature=%s",
            consumerKey, accessToken, oAuthSig);

    // 1. Http Configuration
    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("https://api.twitter.com/2");

    // 2. Scenario Builder (Reading a twitter tweet using auth)
    private ScenarioBuilder scenarioBuilder = scenario("Twitter API Test")
            .exec(http("Post Tweet")
                    .post("/tweets")
                    .header("Content-Type", "application/json")
                    .header("Authorization", oAuth)
                    .body(StringBody("{ \"text\": \"Tweet from Gatling Test!\" }"))
                    .check(status().is(201)))
            .pause(1);

    // 3. Load simulation
    // To not over use the free API I have simulated only for one user but
    // it can be also simulated for many users using Sessions in Gatling.
    {
        setUp(scenarioBuilder.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
    }

}
