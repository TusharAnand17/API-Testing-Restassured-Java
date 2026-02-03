package org.example.api.clients;

import io.restassured.response.Response;
import org.example.api.config.ConfigManager;
import org.example.api.constants.ApiEndpoints;

import java.util.Map;

public class ObjectClient extends BaseApiClient{
    public ObjectClient(){
        super("base.url");
    }

    public Response fetchObjects() {
        return get(ApiEndpoints.OBJECTS, null, null,null);
    }

    public Response fetchObjects(Map<String, Object> queryParams) {
        return get(ApiEndpoints.OBJECTS, null, queryParams,null);
    }

    public Response fetchObjectById(String objectId) {
        return get(ApiEndpoints.OBJECTS + "/" + objectId, null, null,null);
    }

    public Response createObject(Object request) {
        return post(ApiEndpoints.OBJECTS, request, null, null,null,false);
    }
}