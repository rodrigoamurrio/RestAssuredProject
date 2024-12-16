package api.base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

public class BaseService {

    private static final String BASE_URL = "http://localhost:8080/";

    private final RequestSpecification requestSpecification;

    public BaseService() {
        requestSpecification = RestAssured.given().filter(new AllureRestAssured()).baseUri(BASE_URL);
    }

    protected Response postRequest(Object body, String endPoint) {
        return requestSpecification.contentType(ContentType.JSON).body(body).post(endPoint);
    }

    protected Response getRequest(HashMap<String, String> parameters, String endPoint) {
        return requestSpecification.pathParams(parameters).get(endPoint);
    }

    protected Response putRequest(HashMap<String, String> parameters, Object body, String endPoint) {
        return requestSpecification.pathParams(parameters).body(body).put(endPoint);
    }

    protected Response deleteRequest(HashMap<String, String> parameters, String endPoint) {
        return requestSpecification.pathParams(parameters).delete(endPoint);
    }
}
