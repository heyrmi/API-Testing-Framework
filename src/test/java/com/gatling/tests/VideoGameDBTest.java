package com.gatling.tests;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import java.time.Duration;

public class VideoGameDBTest extends Simulation {

    private HttpProtocolBuilder httpProtocol = http.baseUrl("https://videogamedb.uk/api")
            .acceptHeader("application/json");

    private ScenarioBuilder scenarioBuilder = scenario("VideoGameDB Tests")

            .exec(http("Get all video games").get("/videogame")).pause(5)

            .exec(http("Get one video game").get("/videogame/1")).pause(1, 10)

            .exec(http("Get all video games").get("/videogame")).pause(Duration.ofMillis(2000));

    {
        setUp(scenarioBuilder.injectOpen(atOnceUsers(10))).protocols(httpProtocol);
    }

}
