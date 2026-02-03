package org.example.api.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.example.api.clients.BookingClient;
import org.example.api.model.response.BookingIdResponse;
import org.example.api.model.response.BookingResponse;
import org.example.api.support.ApiContext;
import org.example.api.support.DataStore;
import org.example.api.support.DataStoreKeyEnum;
import org.example.api.support.ScenarioState;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class BookingSteps {
    private final ApiContext apiContext;
    private final DataStore dataStore;
    private final BookingClient bookingClient;
    private final ScenarioState scenarioState;

    public BookingSteps(ApiContext apiContext, DataStore dataStore, BookingClient bookingClient, ScenarioState scenarioState){
        this.apiContext = apiContext;
        this.dataStore = dataStore;
        this.bookingClient = bookingClient;
        this.scenarioState = scenarioState;
    }

    @When("I request all booking IDs")
    public void iRequestAllBookingIDs() {
        Response response = bookingClient.getAllBookingIds();
        apiContext.setResponse(response);
    }

    @And("the response should contain a list of booking ids")
    public void theResponseShouldContainAListOfBookingIds() {
        Response response = apiContext.getResponse();
        List<BookingIdResponse> bookingResponseList = Arrays.asList(response.as(BookingIdResponse[].class));
        assertThat(bookingResponseList).isNotEmpty();
    }

    @When("I fetch booking details using stored booking id")
    public void iFetchBookingDetailsUsingStoredBookingId() {
        int bookingId = dataStore.get(DataStoreKeyEnum.BOOKING_ID, Integer.class);
        Response response = bookingClient.getBookingById(bookingId);
        apiContext.setResponse(response);

        BookingResponse bookingResponse = response.as(BookingResponse.class);
        scenarioState.setBookingId(bookingId);
        scenarioState.setBookingDetails(bookingResponse);

        dataStore.put(DataStoreKeyEnum.BOOKING_ID, bookingId);
    }
}
