package com.gatling.tests;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class GoogleTest extends Simulation {

        // Simple Gatling Test

        // 1. Http Configuration
        private HttpProtocolBuilder httpProtocol = http
                        .baseUrl("https://www.google.com")
                        .acceptHeader("application/json");

        // 2. Scenario Builder
        private ScenarioBuilder scenarioBuilder = scenario("GoogleTest")
                        .exec(http("Search Request")
                                        .get("/search?q=Attack+On+Titan")
                                        .check(status().is(200)))
                        .pause(1);
        // 3. Load simulation
        {
                setUp(scenarioBuilder.injectOpen(atOnceUsers(10))).protocols(httpProtocol);
        }
}