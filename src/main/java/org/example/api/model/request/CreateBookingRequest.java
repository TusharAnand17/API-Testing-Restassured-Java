package org.example.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateBookingRequest {

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("totalprice")
    private int totalprice;

    @JsonProperty("depositpaid")
    private boolean depositpaid;

    @JsonProperty("bookingdates")
    private CreateBookingDates bookingdates;

    @JsonProperty("additionalneeds")
    private String additionalneeds;

    private CreateBookingRequest(Builder builder) {
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.totalprice = builder.totalprice;
        this.depositpaid = builder.depositpaid;
        this.bookingdates = builder.bookingdates;
        this.additionalneeds = builder.additionalneeds;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String firstname;
        private String lastname;
        private int totalprice;
        private boolean depositpaid;
        private CreateBookingDates bookingdates;
        private String additionalneeds;

        public Builder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder totalprice(int totalprice) {
            this.totalprice = totalprice;
            return this;
        }

        public Builder depositpaid(boolean depositpaid) {
            this.depositpaid = depositpaid;
            return this;
        }

        public Builder bookingDates(CreateBookingDates bookingdates) {
            this.bookingdates = bookingdates;
            return this;
        }

        public Builder additionalneeds(String additionalneeds) {
            this.additionalneeds = additionalneeds;
            return this;
        }

        public CreateBookingRequest build() {
            return new CreateBookingRequest(this);
        }
    }
}
