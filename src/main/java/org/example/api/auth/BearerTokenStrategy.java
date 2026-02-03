package org.example.api.auth;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.example.api.config.ConfigManager;

import java.util.Map;

public class BearerTokenStrategy implements AuthStrategy{

    private static String token;

    @Override
    public void applyAuth(RequestSpecification requestSpecification, String token) {
        if(token == null || token.isEmpty()){
            throw new IllegalStateException("Bearer token is required but not provided");
        }
        requestSpecification.header("Authorization", "Bearer " + token);
    }

//    private String generateToken(){
//        return RestAssured.given()
//                .baseUri(ConfigManager.INSTANCE.get("auth.baseUri"))
//                .contentType(ContentType.JSON)
//                .body(Map.of(
//                        "username",ConfigManager.INSTANCE.get("auth.username"),
//                        "password",ConfigManager.INSTANCE.get("auth.password")
//                ))
//                .post(ConfigManager.INSTANCE.get("auth.Endpoint"))
//                .then()
//                .statusCode(200)
//                .extract()
//                .path("token");
//    }
}