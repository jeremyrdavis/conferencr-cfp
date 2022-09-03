package io.conferencr.cfp.infrastructure;

import io.conferencr.domain.Speaker;
import io.conferencr.cfp.domain.SpeakerRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

@QuarkusTest
public class PaperResourceTest {

    private static final Logger LOGGER = getLogger(PaperResourceTest.class);

    static final String URL_UNDER_TEST = "/papers";

    static Speaker speaker = new Speaker("lemmy@motorhead.com", "Lemmy", "Kilminster");

    @Inject
    SpeakerRepository speakerRepository;

    @BeforeAll @Transactional
    public static void setUp() {
        speaker.persist();
    }

    @Test @Order(2)
    public void testGetAbstracts() {

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when().get(URL_UNDER_TEST)
                .then()
                .extract().response();

        assertEquals(200, response.statusCode());
    }

    @Test @Order(1)
    public void testAddingSessionAbstract() {

        RestAssured.defaultParser = Parser.JSON;

        String title = "An Abstract for Conferencr";
        String slug = "This is a great abstract";
        String body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ac nunc ut nisi bibendum varius in eget metus. Maecenas sit amet mollis mauris. Suspendisse blandit pharetra sapien quis ultrices. Donec sodales mollis erat eget consectetur. Aliquam erat volutpat. Curabitur a nibh venenatis, facilisis dui quis, vehicula mi. Suspendisse suscipit rutrum maximus. Donec id consequat neque. Etiam lectus neque, luctus vel ligula at, hendrerit molestie magna.%nSed feugiat volutpat nisi eget egestas. Cras consequat lacus non diam maximus lacinia. Donec consectetur pellentesque consectetur. Fusce venenatis porta nibh ut tempus. In congue ante nec erat ultricies, in condimentum elit scelerisque. Interdum et malesuada fames ac ante ipsum primis in faucibus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aenean vulputate purus quis ultrices varius. Phasellus malesuada laoreet scelerisque. In tristique lacinia turpis id semper.%nQuisque in sem velit. In non luctus dui, vitae imperdiet sem. Vivamus aliquam quam eget enim eleifend maximus. Nam sed molestie purus, quis gravida odio. Ut ac elementum neque, et efficitur nulla. Sed id nisi semper, fringilla orci hendrerit, molestie mi. Phasellus ornare eros vel sapien dictum, vel bibendum libero elementum.%nMorbi nisi risus, mattis ac finibus a, bibendum volutpat sem. Sed quis lorem felis. Integer a felis nibh. In a imperdiet neque. Suspendisse blandit mauris commodo, rutrum turpis sit amet, suscipit felis. Sed non vulputate mauris. Vivamus ornare dolor quis nibh posuere, et varius augue iaculis.";

        String requestBody = """
                {
                    "title":"%s",
                    "slug":"%s",
                    "body":"%s",
                    "speakerEmail":"%s"
                }
                """.formatted(title, slug, body, speaker.getEmail());

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

        assertEquals(201, response.statusCode());
        assertEquals(title, response.jsonPath().getString("title"));
        assertEquals(slug, response.jsonPath().getString("slug"));
        assertEquals(body, response.jsonPath().getString("body"));
        assertEquals(speaker.getEmail(), response.jsonPath().getString("speaker.email"));
    }

}
