package org.example.api.auth;

import io.restassured.specification.RequestSpecification;

public interface AuthStrategy {
    void applyAuth(RequestSpecification requestSpecification,String token);
}