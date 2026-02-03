package org.example.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CreateBookingDates {
    @JsonProperty("checkin")
    private final String checkin;

    @JsonProperty("checkout")
    private final String checkout;

    private CreateBookingDates(Builder builder){
        this.checkin = builder.checkin;
        this.checkout = builder.checkout;
    }

    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String checkin;
        private String checkout;

        public Builder checkin(String checkin){
            this.checkin = checkin;
            return this;
        }

        public Builder checkout(String checkout) {
            this.checkout = checkout;
            return this;
        }

        public CreateBookingDates build(){
            if(checkin == null || checkout == null){
                throw new IllegalStateException("checkin and checkout are required");
            }
            return new CreateBookingDates(this);
        }
    }
}
