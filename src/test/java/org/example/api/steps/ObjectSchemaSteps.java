package org.example.api.steps;

import io.cucumber.java.en.Then;
import org.example.api.support.ApiContext;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ObjectSchemaSteps {
    private final ApiContext apiContext;

    public ObjectSchemaSteps(ApiContext apiContext){
        this.apiContext = apiContext;
    }

    @Then("the response should match the object schema")
    public void the_response_should_match_the_object_schema() {
        apiContext.getResponse()
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/object-schema.json"));
    }

}