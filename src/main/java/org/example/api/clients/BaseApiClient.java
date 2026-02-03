package org.example.api.clients;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.api.auth.AuthFactory;
import org.example.api.auth.AuthStrategy;
import org.example.api.spec.RequestSpecBuilderUtil;

import java.util.Map;

public abstract class BaseApiClient {
//    protected RequestSpecification request;
//
//    protected BaseApiClient(){
//        this.request = RequestSpecBuilderUtil.getBaseSpec();
//    }
//
//    protected void addHeaders(Map<String,String> headers){
//        if(headers != null && !headers.isEmpty()){
//            request.headers(headers);
//        }
//    }
//
//    protected void addQueryParams(Map<String,Object> queryParams){
//        if(queryParams != null && !queryParams.isEmpty()){
//            request.queryParams(queryParams);
//        }
//    }
//
//    protected void setBody(Object body){
//        if(body != null){
//            request.body(body);
//        }
//    }
//
//    protected Response get(String endpoint){
//        return request.when().get(endpoint);
//    }
//
//    protected Response post(String endpoint){
//        return request.when().post(endpoint);
//    }
//
//    protected Response put(String endpoint){
//        return request.when().put(endpoint);
//    }
//
//    protected Response delete(String endpoint){
//        return request.when().delete(endpoint);
//    }

    private final String baseUrlKey;
    private String token;

    protected BaseApiClient(String baseUrlKey){
        this.baseUrlKey = baseUrlKey;
    }

    public void setToken(String token){
        this.token = token;
    }

    private RequestSpecification newRequest(boolean requireAuth) {
        RequestSpecification request = RequestSpecBuilderUtil.getBaseSpec(baseUrlKey);

        if(requireAuth && token != null && !token.isBlank()){
            AuthStrategy strategy = AuthFactory.getStrategy();
            strategy.applyAuth(request, token);
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

    protected Response delete(String endpoint,boolean requireAuth) {
        return newRequest(requireAuth).when().delete(endpoint);
    }
}