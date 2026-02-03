package org.example.api.clients;

import io.restassured.response.Response;
import org.example.api.config.ConfigManager;
import org.example.api.constants.ApiEndpoints;

public class AuthClient extends BaseApiClient{
    public AuthClient(){
        super("heroku.base.url");
    }

    public Response createToken(Object body){
        return post(ApiEndpoints.CREATE_TOKEN, body, null, null, null, false);
    }
}
