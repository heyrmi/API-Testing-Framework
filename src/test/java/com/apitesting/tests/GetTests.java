package com.apitesting.tests;

import com.apitesting.utils.ApiUtils;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;

public class GetTests {




    @Test
    public void getUsersDetails(){
        Response response = ApiUtils.buildRequestForGetCalls()
                .get("/api/users?page=2");

        response.prettyPrint();

        System.out.println("\n" + response.getStatusCode());

        //We can use the static imports to reduce verbosity
        assertThat(response.getStatusCode()).isEqualTo(200);
        //Or we can also do normal imports
        Assertions.assertThat(response.jsonPath().getMap("$").size())
                .as("Comparing the size of the map").isEqualTo(6);
    }

    @Test
    public void getUserDetails(){
        Response response = ApiUtils.buildRequestForGetCalls()
                .pathParams("id", 2)
                .get("/api/users/{id}");

        response.prettyPrint();

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
