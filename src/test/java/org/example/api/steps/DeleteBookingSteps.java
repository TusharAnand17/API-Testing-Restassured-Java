package org.example.api.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.example.api.clients.BookingClient;
import org.example.api.support.ApiContext;
import org.example.api.support.DataStore;
import org.example.api.support.ScenarioState;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DeleteBookingSteps {
    private final ApiContext apiContext;
    private final DataStore dataStore;
    private final ScenarioState scenarioState;
    private final BookingClient bookingClient;

    public DeleteBookingSteps(ApiContext apiContext, DataStore dataStore, BookingClient bookingClient, ScenarioState scenarioState){
        this.apiContext = apiContext;
        this.dataStore = dataStore;
        this.bookingClient = bookingClient;
        this.scenarioState = scenarioState;
    }

    @When("I delete the booking with id {int}")
    public void i_delete_the_booking_with_id(Integer bookingId) {
        Response response = bookingClient.deleteBooking(bookingId);
        apiContext.setResponse(response);
    }

    @Then("the booking should be deleted successfully")
    public void the_booking_should_be_deleted_successfully() {
        Response response = apiContext.getResponse();
        assertThat(response.getStatusCode()).isEqualTo(201);
    }
}
