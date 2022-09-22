package io.conferencr.agenda;

import io.conferencr.agenda.api.PresenterRecord;
import io.conferencr.agenda.api.SessionRecord;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class AgendaResourceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgendaResourceTest.class);

    @Inject
    AgendaRepository agendaRepository;

    private String URL_UNDER_TEST ="/agenda";

    @BeforeEach
    @Transactional
    public void setUp() {

        Agenda agenda = Agenda.createForEvent("Test Event");
        agendaRepository.persist(agenda);
    }


    @Test
    public void testAddingSession() {

        SessionRecord sessionRecord = new SessionRecord(null,
                "Test title",
                "Test slug",
                "A test presentation.",
                new ArrayList<PresenterRecord>(){{
                    add(new PresenterRecord("spock@enterprise.mil"));
                }}
        );

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .and()
                        .body(sessionRecord)
                        .when().post(URL_UNDER_TEST)
                        .then()
                        .extract().response();

        LOGGER.info("response: {}", response.asString());

        assertEquals(201, response.statusCode());
        LOGGER.info("received json: {}", response.jsonPath().prettyPrint());
        assertEquals(sessionRecord.presenters().get(0).email(), response.jsonPath().getString("presenters[0].email"));

    }
}
