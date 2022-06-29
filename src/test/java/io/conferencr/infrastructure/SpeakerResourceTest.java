package io.conferencr.infrastructure;

import io.conferencr.domain.Speaker;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

@QuarkusTest
public class SpeakerResourceTest {

    private static final Logger LOGGER = getLogger(SpeakerResourceTest.class);

    @Test @Order(1)
    public void testCreatingSpeaker() {

        Speaker speakerToCreate = new Speaker("pete@buzzcocks.com", "Pete", "Shelley");

        String requestBody = """
                {
                    "email":"%s",
                    "firstName":"%s",
                    "lastName":"%s"
                }
                """.formatted(speakerToCreate.getEmail(), speakerToCreate.getFirstName(), speakerToCreate.getLastName());

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .and()
                        .body(requestBody)
                        .when().post("/speakers")
                        .then()
                        .extract().response();

        assertEquals(201, response.statusCode());
        assertEquals(speakerToCreate.getEmail(), response.jsonPath().getString("email"));
        assertEquals(speakerToCreate.getFirstName(), response.jsonPath().getString("firstName"));
        assertEquals(speakerToCreate.getLastName(), response.jsonPath().getString("lastName"));
    }

    @Test
    @Order(2)
    public void getSpeakers() {

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when().get("/speakers")
                .then()
                .extract().response();

        LOGGER.debug(response.asPrettyString());

        assertEquals(200, response.statusCode());

    }
}
