package org.example.api.support;

import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ResponseValidator {
    public static void validateSchema(Response response, String schemaPath){
        response.then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath(schemaPath));
    }
}
