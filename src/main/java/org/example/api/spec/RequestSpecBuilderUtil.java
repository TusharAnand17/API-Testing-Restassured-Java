package org.example.api.spec;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.example.api.auth.AuthFactory;
import org.example.api.auth.AuthStrategy;
import org.example.api.config.ConfigManager;
import org.example.api.serializers.ObjectMapperFactory;

public class RequestSpecBuilderUtil {
    public static RequestSpecification getBaseSpec(String baseUrlKey){
        RequestSpecification spec = RestAssured.given()
                .baseUri(ConfigManager.INSTANCE.get(baseUrlKey))
                .contentType(ContentType.JSON)
                .accept("application/json")
                .config(RestAssuredConfig.config()
                        .objectMapperConfig(ObjectMapperConfig.objectMapperConfig()
                                .jackson2ObjectMapperFactory((cls, charset) -> ObjectMapperFactory.get())));
        return spec;
    }
}

//        RequestSpecBuilder builder = new RequestSpecBuilder()
//                .setBaseUri(ConfigManager.INSTANCE.get("base.url"))
//                .setContentType(ContentType.JSON)
//                .setAccept(ContentType.JSON)
//                .setConfig(RestAssuredConfig.config()
//                        .objectMapperConfig(ObjectMapperConfig.objectMapperConfig()
//                                .jackson2ObjectMapperFactory((cls, charset) -> ObjectMapperFactory.get())
//                        ));

//        RequestSpecification spec = builder.build();
//
//        AuthStrategy authStrategy = AuthFactory.getStrategy();
//        authStrategy.applyAuth(spec);
//
//        spec.log().ifValidationFails(LogDetail.ALL);
//
//        return spec;