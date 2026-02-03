package org.example.api.support;

import io.restassured.response.Response;
import java.util.Map;

public class ApiContext {
    private String endpoint;
    private Object requestBody;
    private Map<String, ?> requestHeaders;
    private Response response;

    public ApiContext() {}

    // ---------- Request ----------

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Object getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(Object requestBody) {
        this.requestBody = requestBody;
    }

    public Map<String, ?> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Map<String, ?> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    // ---------- Response ----------

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public int getStatusCode() {
        return response != null ? response.getStatusCode() : -1;
    }

    // ---------- Utility ----------

    public void reset() {
        endpoint = null;
        requestBody = null;
        requestHeaders = null;
        response = null;
    }
}
