package com.apitesting.pojo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(setterPrefix = "set")
public class User {
     /*
            {
                "name": "morpheus",
                "job": "leader"
            }
     */

    private String name;
    private String jobTitle;

}
