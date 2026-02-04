package org.example.api.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.example.api.clients.BookingClient;
import org.example.api.model.request.CreateBookingDates;
import org.example.api.model.request.CreateBookingRequest;
import org.example.api.model.response.BookingResponse;
import org.example.api.support.ApiContext;
import org.example.api.support.DataStore;
import org.example.api.support.DataStoreKeyEnum;
import org.example.api.support.ScenarioState;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UpdateBookingSteps {
    private final ApiContext apiContext;
    private final ScenarioState scenarioState;
    private final BookingClient bookingClient;
    private final DataStore dataStore;

    public UpdateBookingSteps(ApiContext apiContext, ScenarioState scenarioState, BookingClient bookingClient, DataStore dataStore) {
        this.apiContext = apiContext;
        this.scenarioState = scenarioState;
        this.bookingClient = bookingClient;
        this.dataStore = dataStore;
    }

    @Given("an existing booking with id {int}")
    public void an_existing_booking_with_id(Integer bookingId) {
        scenarioState.setBookingId(bookingId);
        dataStore.put(DataStoreKeyEnum.BOOKING_ID,bookingId);

        Response response = bookingClient.getBookingById(bookingId);
        apiContext.setResponse(response);

        assertThat(response.getStatusCode()).as("Booking with id " + bookingId + " should exist") .isEqualTo(200);

        BookingResponse bookingResponse = response.as(BookingResponse.class);
        scenarioState.setBookingDetails(bookingResponse);
    }

    @When("I update the booking with firstname {string}, lastname {string}, total price {int}, deposit paid {string}, checkin {string}, checkout {string}, and additional needs {string}")
    public void i_update_the_booking(String firstname, String lastname, Integer totalprice, String depositpaid, String checkin, String checkout, String additionalneeds) {
        boolean depositPaidBool = Boolean.parseBoolean(depositpaid);

            CreateBookingDates bookingDates = CreateBookingDates.builder()
                    .checkin(checkin)
                    .checkout(checkout)
                    .build();

            CreateBookingRequest request = CreateBookingRequest.builder()
                    .firstname(firstname)
                    .lastname(lastname)
                    .totalprice(totalprice)
                    .depositpaid(depositPaidBool)
                    .bookingDates(bookingDates)
                    .additionalneeds(additionalneeds)
                    .build();

            int bookingId = dataStore.get(DataStoreKeyEnum.BOOKING_ID, Integer.class);
            Response response = bookingClient.updateBooking(bookingId, request);
            apiContext.setResponse(response);
            assertThat(response.getStatusCode()).as("Booking update should return 200 OK").isEqualTo(200);
    }

    @Then("the booking should be updated successfully")
    public void the_booking_should_be_updated_successfully() {
        Response response = apiContext.getResponse(); assertThat(response.getStatusCode()) .as("Booking update should return 200 OK") .isEqualTo(200);
        System.out.println(response.asString());
    }
}
