package org.example.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingIdResponse {
    @JsonProperty("bookingid")
    private int bookingid;

    public int getBookingid() {
        return bookingid;
    }
}
