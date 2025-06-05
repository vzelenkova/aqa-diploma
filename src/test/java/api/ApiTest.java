package api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    private final String baseUri = "http://localhost:8080";

    @Test
    void shouldReturnSuccessForApprovedCardPayment() {
        given()
            .baseUri(baseUri)
            .contentType(ContentType.JSON)
            .body("{\"number\":\"4444 4444 4444 4441\",\"month\":\"12\",\"year\":\"25\",\"holder\":\"Иван Иванов\",\"cvc\":\"123\"}")
        .when()
            .post("/api/v1/pay")
        .then()
            .statusCode(200)
            .body("status", equalTo("APPROVED"));
    }

    @Test
    void shouldReturnErrorForDeclinedCardPayment() {
        given()
            .baseUri(baseUri)
            .contentType(ContentType.JSON)
            .body("{\"number\":\"4444 4444 4444 4442\",\"month\":\"12\",\"year\":\"25\",\"holder\":\"Иван Иванов\",\"cvc\":\"123\"}")
        .when()
            .post("/api/v1/pay")
        .then()
            .statusCode(200)
            .body("status", equalTo("DECLINED"));
    }

    @Test
    void shouldReturnSuccessForApprovedCardCredit() {
        given()
            .baseUri(baseUri)
            .contentType(ContentType.JSON)
            .body("{\"number\":\"4444 4444 4444 4441\",\"month\":\"12\",\"year\":\"25\",\"holder\":\"Иван Иванов\",\"cvc\":\"123\"}")
        .when()
            .post("/api/v1/credit")
        .then()
            .statusCode(200)
            .body("status", equalTo("APPROVED"));
    }

    @Test
    void shouldReturnErrorForDeclinedCardCredit() {
        given()
            .baseUri(baseUri)
            .contentType(ContentType.JSON)
            .body("{\"number\":\"4444 4444 4444 4442\",\"month\":\"12\",\"year\":\"25\",\"holder\":\"Иван Иванов\",\"cvc\":\"123\"}")
        .when()
            .post("/api/v1/credit")
        .then()
            .statusCode(200)
            .body("status", equalTo("DECLINED"));
    }

    @Test
    void shouldReturnValidationErrorForInvalidFields() {
        given()
            .baseUri(baseUri)
            .contentType(ContentType.JSON)
            .body("{\"number\":\"\",\"month\":\"\",\"year\":\"\",\"holder\":\"\",\"cvc\":\"\"}")
        .when()
            .post("/api/v1/pay")
        .then()
            .statusCode(400);
    }
}

