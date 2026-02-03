package org.example.api.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.example.api.clients.ObjectClient;
import org.example.api.model.request.CreateObjectData;
import org.example.api.model.request.CreateObjectRequest;
import org.example.api.model.response.ObjectResponse;
import org.example.api.support.ApiContext;
import org.example.api.support.DataStore;
import org.example.api.support.DataStoreKeyEnum;
import org.example.api.support.ScenarioState;
import static org.assertj.core.api.Assertions.assertThat;
import static org.example.api.spec.ResponseSpecBuilderUtil.getSuccessSpec;

public class ObjectSteps {
    private final ObjectClient objectClient;
    private final ApiContext apiContext;
    private final ScenarioState scenarioState;
    private final DataStore dataStore;

    // PicoContainer will inject these
    public ObjectSteps(ObjectClient objectClient,
                       ApiContext apiContext,
                       ScenarioState scenarioState,
                       DataStore dataStore) {
        this.objectClient = objectClient;
        this.apiContext = apiContext;
        this.scenarioState = scenarioState;
        this.dataStore = dataStore;
    }

    @Given("a valid object creation payload")
    public void a_valid_object_creation_payload(){
        CreateObjectData data = CreateObjectData.builder()
                .year(2025)
                .price(1849.99)
                .cpuModel("Intel Core i9")
                .hardDiskSize("90 TB")
                .build();

        CreateObjectRequest request = CreateObjectRequest.builder()
                .name("Apple MacBook Pro 20")
                .data(data)
                .build();

        apiContext.setRequestBody(request);
        dataStore.put(DataStoreKeyEnum.EXPECTED_RESULT,request);
    }

    @When("I create the object")
    public void i_create_the_object() {
        Response response = objectClient.createObject(apiContext.getRequestBody());
        apiContext.setResponse(response);

//        response.then().spec(getCreatedSpec());

        ObjectResponse actual = response.as(ObjectResponse.class);

        // Store the created object ID globally
        // String objectId = response.jsonPath().getString("id");      // this is also possible: response.then().extract().path("id");

        dataStore.put(DataStoreKeyEnum.ACTUAL_RESULT,actual);
        scenarioState.setObjectId(actual.getId());
        dataStore.put(DataStoreKeyEnum.LAST_CREATED_OBJECT_ID,actual.getId());
    }

    @When("I fetch the object by id")
    public void i_fetch_the_object_by_id() {
        String objectId = dataStore.get(DataStoreKeyEnum.LAST_CREATED_OBJECT_ID, String.class);
        Response response = objectClient.fetchObjectById(objectId);
        apiContext.setResponse(response);

        response.then().spec(getSuccessSpec());

        ObjectResponse actual = response.as(ObjectResponse.class);
        dataStore.put(DataStoreKeyEnum.ACTUAL_RESULT,actual);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int expectedStatusCode) {
       assertThat(apiContext.getStatusCode()).isEqualTo(expectedStatusCode);
    }

    @Then("the response should contain the object name")
    public void the_response_should_contain_the_object_name() {
//        Response response = apiContext.getResponse();
//        String name = response.jsonPath().getString("name");
//        assertThat("Response does not contain object name", name, equalTo("Apple MacBook Pro 20"));

        ObjectResponse actual = dataStore.get(DataStoreKeyEnum.ACTUAL_RESULT,ObjectResponse.class);
        CreateObjectRequest expected = dataStore.get(DataStoreKeyEnum.EXPECTED_RESULT,CreateObjectRequest.class);
        assertThat(actual.getName()).isEqualTo(expected.getName());
    }

    @Then("the response should contain data fields")
    public void the_response_should_contain_data_fields() {
        ObjectResponse actual = dataStore.get(DataStoreKeyEnum.ACTUAL_RESULT, ObjectResponse.class);
        CreateObjectRequest expected = dataStore.get(DataStoreKeyEnum.EXPECTED_RESULT,CreateObjectRequest.class);

        assertThat(actual.getData().getYear()).isEqualTo(expected.getData().getYear());
        assertThat(actual.getData().getPrice()).isEqualTo(expected.getData().getPrice());
        assertThat(actual.getData().getCpuModel()).isEqualTo(expected.getData().getCpuModel());
        assertThat(actual.getData().getHardDiskSize()).isEqualTo(expected.getData().getHardDiskSize());
    }
}

