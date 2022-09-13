package io.conferencr.attendee.infrastructure;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

@QuarkusTest
public class AttendeeResourceTest {

    private static final Logger LOGGER = getLogger(AttendeeResourceTest.class);
    static final String URL_UNDER_TEST = "/attendees";

    static final String ATTENDEE_EMAIL = "eyore@ashdownforest.org";

    @Test
    @Order(2)
    public void testGetAttendees() {

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when().get(URL_UNDER_TEST)
                .then()
                .extract().response();

        assertEquals(200, response.statusCode());
        assertEquals(ATTENDEE_EMAIL, response.jsonPath().getString("email[0]"));
    }

    @Test @Order(1)
    public void testRegisteringAttendee() {

        RestAssured.defaultParser = Parser.JSON;

        String requestBody = """
                {
                    "email":"%s"
                }
                """.formatted(ATTENDEE_EMAIL);

        LOGGER.debug(requestBody);

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .and()
                        .body(requestBody)
                        .when().post(URL_UNDER_TEST)
                        .then()
                        .extract().response();

        LOGGER.info("response: {}", response.asString());

        assertEquals(201, response.statusCode());
        assertEquals(ATTENDEE_EMAIL, response.jsonPath().getString("email"));
    }

}
