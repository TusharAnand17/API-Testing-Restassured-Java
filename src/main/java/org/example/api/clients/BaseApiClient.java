package org.example.api.clients;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.api.spec.RequestSpecBuilderUtil;

import java.util.Map;

public abstract class BaseApiClient {
    private final String baseUrlKey;
    private String token;
    private String username;
    private String password;

    protected BaseApiClient(String baseUrlKey){
        this.baseUrlKey = baseUrlKey;
    }

    public void setToken(String token){
        this.token = token;
    }

    public void setBasicAuth(String username,String password){
        this.username = username;
        this.password = password;
    }

    private RequestSpecification newRequest(boolean requireAuth) {
        RequestSpecification request = RequestSpecBuilderUtil.getBaseSpec(baseUrlKey);

//        if require token
        if(requireAuth && token != null && !token.isBlank()){
//            AuthStrategy strategy = AuthFactory.getStrategy();
//            strategy.applyAuth(request, token);
            request.header("Cokkie","token=" + token);
        }

        if (requireAuth && username != null && password != null){
            request.auth().preemptive().basic(username,password);
        }
        return request;
    }


    protected Response get(String endpoint, Map<String,String> headers, Map<String,Object> queryParams,Map<String,Object>pathParams) {
        RequestSpecification request = newRequest(false);

        if(headers != null && !headers.isEmpty()) request.headers(headers);
        if(queryParams != null && !queryParams.isEmpty()) request.queryParams(queryParams);
        if(pathParams != null && !pathParams.isEmpty()) request.pathParams(pathParams);

        return request.when().get(endpoint);
    }


    protected Response post(String endpoint, Object body, Map<String, String> headers, Map<String, Object> queryParams, Map<String, Object> pathParams, boolean requireAuth) {
        RequestSpecification request = newRequest(requireAuth);
        if (headers != null && !headers.isEmpty()) request.headers(headers);
        if (queryParams != null && !queryParams.isEmpty()) request.queryParams(queryParams);
        if (pathParams != null && !pathParams.isEmpty()) request.pathParams(pathParams);
        if (body != null) request.body(body);
        return request.when().post(endpoint);
    }


    protected Response put(String endpoint, Object body, Map<String, String> headers, Map<String, Object> queryParams, Map<String, Object> pathParams, boolean requireAuth) {
        RequestSpecification request = newRequest(requireAuth);
        if (headers != null && !headers.isEmpty()) request.headers(headers);
        if (queryParams != null && !queryParams.isEmpty()) request.queryParams(queryParams);
        if (pathParams != null && !pathParams.isEmpty()) request.pathParams(pathParams);
        if (body != null) request.body(body);
        return request.when().put(endpoint);
    }

    protected Response delete(String endpoint,Map<String, String> headers, Map<String, Object> queryParams, Map<String, Object> pathParams, boolean requireAuth) {
        RequestSpecification request = newRequest(requireAuth);
        if(headers != null && !headers.isEmpty()) request.headers(headers);
        if(queryParams != null && !queryParams.isEmpty()) request.queryParams(queryParams);
        return request.when().delete(endpoint);
    }
}