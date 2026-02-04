package org.example.api.auth;


import io.restassured.specification.RequestSpecification;


public class BearerTokenStrategy implements AuthStrategy{

    private static String token;

    @Override
    public void applyAuth(RequestSpecification requestSpecification, String token) {
        if(token == null || token.isEmpty()){
            throw new IllegalStateException("Bearer token is required but not provided");
        }
        requestSpecification.header("Authorization", "Bearer " + token);
    }
}