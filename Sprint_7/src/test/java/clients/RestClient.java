package clients;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestClient {
    private final static String BASE_URI = "https://qa-scooter.praktikum-services.ru";
    protected static RequestSpecification getDefaultRequestSpecification() {
        return given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON);
    }
}
