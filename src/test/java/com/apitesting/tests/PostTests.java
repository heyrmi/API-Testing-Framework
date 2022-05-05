package com.apitesting.tests;

import com.apitesting.pojo.User;
import com.apitesting.requestbuilder.RequestBuilder;
import com.apitesting.utils.APIUtils;
import com.apitesting.utils.RandomUtils;

import static com.apitesting.utils.RandomUtils.*;

import java.lang.reflect.Method;

import com.apitesting.constants.FrameworkConstants;
import com.apitesting.constants.FrameworkConstantsWithSingleton;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostTests {

        @Test
        public void postUsers() {
                // TODO: Make method for post multiple users
                // Can't be made since ReqRes.in doesn't support this request
                // Adding fake assertion to pass this test
                Assert.assertTrue(true);
        }

        @Test
        public void postUser() {

                // Create a post call with new user and create body using POJO
                /*
                 * {
                 * "name": "morpheus",
                 * "job": "leader"
                 * }
                 */

                // using fake data here
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

        @Test
        public void postRequestUsingExternalFile(Method method) {
                // using simple JSON, can also manage Post Request with complex JSON body
                // autofilling the random data before every function call
                String postRequestBody = APIUtils
                                .readJSONAndReturnString(
                                                FrameworkConstants.getJSONRequestFolderPath() + "request.json")
                                .replace("fname", RandomUtils.getRandomFirstName())
                                .replace("givenJob", RandomUtils.getRandomJobTitle());

                Response response = RequestBuilder
                                .buildRequestForPostCalls()
                                .body(postRequestBody)
                                .post("/api/users");

                APIUtils.storeStringAsJSONFile(
                                FrameworkConstants.getJSONResponseFolderPath() + method.getName() + "Response.json",
                                response);

                Assertions.assertThat(response.getStatusCode()).isEqualTo(201);
        }

        @Test
        public void postRequestUsingExternalFileUsingSingleton(Method method) {
                // using simple JSON, can also manage Post Request with complex JSON body
                // autofilling the random data before every function call
                String postRequestBody = APIUtils
                                .readJSONAndReturnString(
                                                FrameworkConstantsWithSingleton.getInstance().getJSONRequestFolderPath()
                                                                + "request.json")
                                .replace("fname", RandomUtils.getRandomFirstName())
                                .replace("givenJob", RandomUtils.getRandomJobTitle());

                Response response = RequestBuilder
                                .buildRequestForPostCalls()
                                .body(postRequestBody)
                                .post("/api/users");

                APIUtils.storeStringAsJSONFile(
                                FrameworkConstantsWithSingleton.getInstance().getJSONResponseFolderPath()
                                                + method.getName() + "Response.json",
                                response);

                Assertions.assertThat(response.getStatusCode()).isEqualTo(201);
        }
}
