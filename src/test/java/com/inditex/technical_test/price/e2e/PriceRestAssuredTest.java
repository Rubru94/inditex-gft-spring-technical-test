package com.inditex.technical_test.price.e2e;

import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.response.Response;

// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) // Use fixed port
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // Use random port
public class PriceRestAssuredTest {

    @LocalServerPort
    private int port; // Injects assigned random port

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Nested
    class GetPriceTests {

        private Response makeRequest(String brandId, String productId, String applicationDate) {
            return RestAssured.given()
                    .param("brandId", brandId)
                    .param("productId", productId)
                    .param("applicationDate", applicationDate)
                    .get("/api/prices");
        }

        @Test
        void shouldReturnPrice_At2020Y14June10AM_Test1() {
            Response response = makeRequest("1", "35455", "2020-06-14 10:00:00");

            response.then()
                    .statusCode(HttpStatus.OK.value())
                    .body("brandId", equalTo(1))
                    .body("productId", equalTo(35455))
                    .body("price", equalTo(35.50f))
                    .body("priority", equalTo(0));
        }

        @Test
        void shouldReturnPrice_At2020Y14June16PM_Test2() {
            Response response = makeRequest("1", "35455", "2020-06-14 16:00:00");

            response.then()
                    .statusCode(HttpStatus.OK.value())
                    .body("brandId", equalTo(1))
                    .body("productId", equalTo(35455))
                    .body("price", equalTo(25.45f))
                    .body("priority", equalTo(1)); // More priority than previous one
        }

        @Test
        void shouldReturnPrice_At2020Y14June21PM_Test3() {
            Response response = makeRequest("1", "35455", "2020-06-14 21:00:00");

            response.then()
                    .statusCode(HttpStatus.OK.value())
                    .body("brandId", equalTo(1))
                    .body("productId", equalTo(35455))
                    .body("price", equalTo(35.50f))
                    .body("priority", equalTo(0));
        }

        @Test
        void shouldReturnPrice_At2020Y15June10AM_Test4() {
            Response response = makeRequest("1", "35455", "2020-06-15 10:00:00");

            response.then()
                    .statusCode(HttpStatus.OK.value())
                    .body("brandId", equalTo(1))
                    .body("productId", equalTo(35455))
                    .body("price", equalTo(30.50f))
                    .body("priority", equalTo(1));
        }

        @Test
        void shouldReturnPrice_At2020Y16June21PM_Test5() {
            Response response = makeRequest("1", "35455", "2020-06-16 21:00:00");

            response.then()
                    .statusCode(HttpStatus.OK.value())
                    .body("brandId", equalTo(1))
                    .body("productId", equalTo(35455))
                    .body("price", equalTo(38.95f))
                    .body("priority", equalTo(1));
        }

        @Test
        void shouldReturn404_WhenNoPriceAvailable() {
            Response response = makeRequest("10", "99999", "2020-06-14 10:00:00");

            response.then()
                    .statusCode(HttpStatus.NOT_FOUND.value());
        }

        @Test
        void shouldReturn400_WhenUseInvalidParams() {
            Response response = makeRequest("invalid", "invalid", "invalid");

            response.then()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }
}
