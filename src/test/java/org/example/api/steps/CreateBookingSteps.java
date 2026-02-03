package org.example.api.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.restassured.response.Response;
import org.example.api.clients.BookingClient;
import org.example.api.model.request.CreateBookingDates;
import org.example.api.model.request.CreateBookingRequest;
import org.example.api.model.response.CreateBookingResponse;
import org.example.api.support.ApiContext;
import org.example.api.support.DataStore;
import org.example.api.support.DataStoreKeyEnum;
import org.example.api.support.ScenarioState;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateBookingSteps {
    private final ApiContext apiContext;
    private final DataStore dataStore;
    private final BookingClient bookingClient;
    private final ScenarioState scenarioState;

    public CreateBookingSteps(ApiContext apiContext, DataStore dataStore, BookingClient bookingClient, ScenarioState scenarioState){
        this.apiContext = apiContext;
        this.bookingClient = bookingClient;
        this.dataStore = dataStore;
        this.scenarioState = scenarioState;
    }

    @Given("a valid booking creation payload with firstname {string}, lastname {string}, total price {int}, deposit paid {string}, checkin {string}, checkout {string}, and additional needs {string}")
    public void a_valid_booking_creation_payload_with_parameters(String firstname, String lastname, Integer totalprice, String depositpaid, String checkin, String checkout, String additionalneeds) {
        // Convert depositPaid string to boolean
        boolean depositPaidBool = Boolean.parseBoolean(depositpaid);

        // Build booking dates
        CreateBookingDates bookingDates = CreateBookingDates.builder()
                .checkin(checkin)
                .checkout(checkout)
                .build();

        // Build booking request dynamically
        CreateBookingRequest request = CreateBookingRequest.builder()
                .firstname(firstname)
                .lastname(lastname)
                .totalprice(totalprice)
                .depositpaid(depositPaidBool)
                .bookingDates(bookingDates)
                .additionalneeds(additionalneeds)
                .build();

        // Store request in ApiContext for later use
        apiContext.setRequestBody(request);
    }


    @When("I create the booking")
    public void i_create_the_booking(){
        Response response = bookingClient.createBooking(apiContext.getRequestBody());
        apiContext.setResponse(response);

        CreateBookingResponse bookingResponse = response.as(CreateBookingResponse.class);
        scenarioState.setBookingId(bookingResponse.getBookingId());
        scenarioState.setCreateBookingResponse(bookingResponse);

        dataStore.put(DataStoreKeyEnum.BOOKING_ID, bookingResponse.getBookingId());
    }

    @Then("the booking should be created successfully")
    public void the_booking_should_be_created_successfully() {
        Response response = apiContext.getResponse();
        assertThat(response.getStatusCode()).isEqualTo(200);

        int bookingId = response.jsonPath().getInt("bookingid");
        dataStore.put(DataStoreKeyEnum.BOOKING_ID, bookingId);

        assertThat(bookingId).isGreaterThan(0);
    }

    @Then("the response should contain a booking id")
    public void response_should_contain_booking_id() {
        assertThat(scenarioState.getBookingId())
                .as("Booking ID should be generated")
                .isNotNull();
    }

    // Unified validation using getCurrentBooking()
    @Then("the booking firstname should be {string}")
    public void booking_firstname_should_be(String expected) {
        assertThat(scenarioState.getCurrentBooking().getFirstname())
                .isEqualTo(expected);
    }

    @Then("the booking lastname should be {string}")
    public void booking_lastname_should_be(String expected) {
        assertThat(scenarioState.getCurrentBooking().getLastname())
                .isEqualTo(expected);
    }

    @Then("the total price should be {int}")
    public void total_price_should_be(Integer expected) {
        assertThat(scenarioState.getCurrentBooking().getTotalprice())
                .isEqualTo(expected);
    }

    @Then("the checkin date should be {string}")
    public void checkin_date_should_be(String expected) {
        assertThat(scenarioState.getCurrentBooking().getBookingdates().getCheckin())
                .isEqualTo(expected);
    }

    @Then("the checkout date should be {string}")
    public void checkout_date_should_be(String expected) {
        assertThat(scenarioState.getCurrentBooking().getBookingdates().getCheckout())
                .isEqualTo(expected);
    }

    @Then("the additional needs should be {string}")
    public void additional_needs_should_be(String expected) {
        assertThat(scenarioState.getCurrentBooking().getAdditionalneeds())
                .isEqualTo(expected);
    }

    @And("the deposit paid status should be {string}")
    public void theDepositPaidStatusShouldBeDepositPaid(String depositPaid) {
        boolean expected = Boolean.parseBoolean(depositPaid);
        assertThat(scenarioState.getCurrentBooking().isDepositpaid())
                .isEqualTo(expected);
    }
}
