package org.example.api.spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.lessThan;

public class ResponseSpecBuilderUtil {
    private static final long MAX_RESPONSE_TIME = 3000;

    public static ResponseSpecification getSuccessSpec(){
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThan(MAX_RESPONSE_TIME))
                .build();
    }

    public static ResponseSpecification getCreatedSpec(){
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(201)
                .expectResponseTime(lessThan(MAX_RESPONSE_TIME))
                .build();
    }

    public static ResponseSpecification getNoContentSpec(){
        return new ResponseSpecBuilder()
                .expectStatusCode(204)
                .expectResponseTime(lessThan(MAX_RESPONSE_TIME))
                .build();
    }
}