package org.example.api.steps;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.api.support.ApiContext;
import org.example.api.support.ResponseValidator;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ObjectSchemaSteps {
    private final ApiContext apiContext;

    public ObjectSchemaSteps(ApiContext apiContext){
        this.apiContext = apiContext;
    }

    @Then("the response should match the object schema")
    public void the_response_should_match_the_object_schema() {
        ResponseValidator responseValidator = new ResponseValidator();
        Response response = apiContext.getResponse();
        responseValidator.validateSchema(response, "schemas/object-schema.json");
    }

}