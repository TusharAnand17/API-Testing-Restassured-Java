package org.example.api.clients;

import io.restassured.response.Response;
import org.codehaus.groovy.util.FastArray;
import org.example.api.constants.ApiEndpoints;
import org.example.api.model.request.CreateBookingRequest;

import java.util.Map;

public class BookingClient extends BaseApiClient{
    public BookingClient(){
        super("heroku.base.url");
    }

    public Response createBooking(Object body){
        return post(ApiEndpoints.CREATE_BOOKING, body, null,null,null,false);  // NO auth
    }

    public Response getAllBookingIds(){
        return get(ApiEndpoints.GET_BOOKING_IDS,null,null,null);
    }

    public Response getBookingById(int bookingId){
        return get(ApiEndpoints.GET_BOOKING_BY_ID,null,null,Map.of("id",bookingId));
    }

    public Response updateBooking(int bookingId, CreateBookingRequest request){
        return put(ApiEndpoints.UPDATE_BOOKING, request, null, null,Map.of("id",bookingId),true);
    }
}
