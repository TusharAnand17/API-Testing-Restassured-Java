package org.example.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateBookingResponse {
    @JsonProperty("bookingid")
    private int bookingId;

    @JsonProperty("booking")
    private BookingResponse booking;

    public int getBookingId() {
        return bookingId;
    }

    public BookingResponse getBooking() {
        return booking;
    }
}
