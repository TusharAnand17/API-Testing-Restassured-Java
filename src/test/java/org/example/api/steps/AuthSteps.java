package org.example.api.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Data;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.api.clients.AuthClient;
import org.example.api.clients.BookingClient;
import org.example.api.config.ConfigManager;
import org.example.api.constants.ApiEndpoints;
import org.example.api.model.request.CreateBookingDates;
import org.example.api.model.request.CreateBookingRequest;
import org.example.api.model.request.CreateTokenRequest;
import org.example.api.model.response.TokenResponse;
import org.example.api.spec.RequestSpecBuilderUtil;
import org.example.api.support.ApiContext;
import org.example.api.support.DataStore;
import org.example.api.support.DataStoreKeyEnum;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AuthSteps {
    private final ApiContext apiContext;
    private final AuthClient authClient;
    private final DataStore dataStore;
    private final BookingClient bookingClient;

    public AuthSteps(ApiContext apiContext, DataStore dataStore, AuthClient authClient, BookingClient bookingClient) {
        this.bookingClient = bookingClient;
        this.apiContext = apiContext;
        this.authClient = authClient;
        this.dataStore = dataStore;
    }

    @Given("valid admin credentials for token generation")
    public void valid_admin_credentials_for_token_generation() {
        CreateTokenRequest request = CreateTokenRequest.builder()
                .username(ConfigManager.INSTANCE.get("auth.username"))
                .password(ConfigManager.INSTANCE.get("auth.password"))
                .build();

        apiContext.setRequestBody(request);
    }

    @When("I request an authentication token")
    public void i_request_an_authentication_token() {
        Response response = authClient.createToken(apiContext.getRequestBody());
        apiContext.setResponse(response);

        TokenResponse tokenResponse = response.as(TokenResponse.class);
        String token = tokenResponse.getToken();

        dataStore.put(DataStoreKeyEnum.AUTH_TOKEN, tokenResponse.getToken());

        bookingClient.setToken(token);
    }

    @Then("a valid token should be generated")
    public void a_valid_token_should_be_generated() {
        String token = dataStore.get(DataStoreKeyEnum.AUTH_TOKEN,String.class);
        assertThat(token)
                .as("Auth token should not be null or empty")
                .isNotNull()
                .isNotEmpty();
        System.out.println(token);
    }
    @Given("valid admin credentials for basic authentication")
    public void valid_admin_credentials_for_basic_authentication() {
        bookingClient.setBasicAuth( ConfigManager.INSTANCE.get("auth.username"), ConfigManager.INSTANCE.get("auth.password") );
    }

}


