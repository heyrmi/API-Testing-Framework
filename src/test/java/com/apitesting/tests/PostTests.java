package com.apitesting.tests;

import com.apitesting.pojo.User;
import com.apitesting.requestbuilder.RequestBuilder;
import static com.apitesting.utils.RandomUtils.*;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class PostTests {

    @Test
    public void postUsers(){

    }

    @Test
    public void postUser() {

        //Create a post call with new user and create body using POJO
        /*
            {
                "name": "morpheus",
                "job": "leader"
            }
        */


        //using fake data here
        User user = User
                .builder()
                .setName(getRandomFirstName())
                .setJobTitle(getRandomJobTitle())
                .build();

        Response response = RequestBuilder
                .buildRequestForPostCalls()
                .body(user)
                .post("/api/users");

        Assertions.assertThat(response.getStatusCode()).isEqualTo(201);

    }
}
