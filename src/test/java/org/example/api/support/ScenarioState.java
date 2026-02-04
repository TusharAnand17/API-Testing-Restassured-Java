package org.example.api.support;

import org.example.api.model.response.BookingResponse;
import org.example.api.model.response.CreateBookingResponse;

import java.util.Map;

public class ScenarioState {

    private String objectId;
    private String objectName;
    private Map<String, Object> objectData;

    private Integer bookingId;

    // Store both creation and retrieval responses
    private CreateBookingResponse createBookingResponse;
    private BookingResponse bookingDetails;

    private String authToken;

    public ScenarioState() {}

    // ---------- Object ----------
    public String getObjectId() { return objectId; }
    public void setObjectId(String objectId) { this.objectId = objectId; }

    public String getObjectName() { return objectName; }
    public void setObjectName(String objectName) { this.objectName = objectName; }

    public Map<String, Object> getObjectData() { return objectData; }
    public void setObjectData(Map<String, Object> objectData) { this.objectData = objectData; }

    // ---------- Booking ----------
    public Integer getBookingId() { return bookingId; }
    public void setBookingId(Integer bookingId) { this.bookingId = bookingId; }

    public void setCreateBookingResponse(CreateBookingResponse response) {
        this.createBookingResponse = response;
    }

    public void setBookingDetails(BookingResponse response) {
        this.bookingDetails = response;
    }

    public BookingResponse getCurrentBooking() {
        if (createBookingResponse != null) {
            return createBookingResponse.getBooking();
        }
        return bookingDetails;
    }

    public String getAuthToken() { return authToken; }
    public void setAuthToken(String authToken) { this.authToken = authToken; }

    public void reset() {
        objectId = null;
        objectName = null;
        objectData = null;
        authToken = null;
        bookingId = null;
        createBookingResponse = null;
        bookingDetails = null;
    }
}
