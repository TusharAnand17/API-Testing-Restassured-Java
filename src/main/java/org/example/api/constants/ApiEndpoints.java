package org.example.api.constants;

import org.mozilla.javascript.StackStyle;

import java.sql.Statement;

public final class ApiEndpoints {
    private ApiEndpoints(){}

    public static final String OBJECTS = "/objects";
    public static final String CREATE_TOKEN = "/auth";
    public static final String CREATE_BOOKING = "/booking";
    public static final String GET_BOOKING_IDS = "/booking";
    public static final String GET_BOOKING_BY_ID = "/booking/{id}";
    public static final String UPDATE_BOOKING = "/booking/{id}";
}
