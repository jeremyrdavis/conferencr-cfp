package io.conferencr.cfp.infrastructure;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ReviewerResourceTest {

    @Test
    public void testAddReviewer() {

        String reviewerEmail = "jake@stifflittlefingers.com";

        String requestBody = """
                {
                    "email":"%s"
                }
                """.formatted(reviewerEmail);

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .and()
                        .body(requestBody)
                        .when().post("/reviewers")
                        .then()
                        .extract().response();

        assertEquals(201, response.statusCode());
        assertEquals(reviewerEmail, response.jsonPath().getString("email"));

    }
}
