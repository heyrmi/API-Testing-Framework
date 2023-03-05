package com.gatling.tests;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class GoogleTest extends Simulation {

    HttpProtocolBuilder httpProtocol = http
            .baseUrl("https://www.google.com");

    ScenarioBuilder scenarioBuilder = scenario("GoogleTest")
            .exec(http("Search Request")
                    .get("/search?q=Attack+On+Titan")
                    .check(status().is(200)))
            .pause(1);

    {
        setUp(scenarioBuilder.injectOpen(atOnceUsers(10))).protocols(httpProtocol);
    }
}