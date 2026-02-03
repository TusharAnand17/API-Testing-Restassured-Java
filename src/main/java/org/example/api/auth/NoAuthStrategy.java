package org.example.api.auth;

import io.restassured.specification.RequestSpecification;

public class NoAuthStrategy implements AuthStrategy{
    @Override
    public void applyAuth(RequestSpecification requestSpecification,String token){
        // No authentication required
    }
}
