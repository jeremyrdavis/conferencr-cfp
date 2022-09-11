package io.conferencr.agenda;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class AgendaServiceTest {

    private static final String SESSION_TITLE = "Session title";
    private static final String SESSION_SLUG = "Session slug";
    private static final String SESSION_DESCRIPTION = "Session description";
    private static final String PRESENTER_EMAIL = "foo@bar.com";

    @Inject
    AgendaService agendaService;

    @Inject
    AgendaRepository agendaRepository;

    Long agendaId;

    @BeforeEach @Transactional
    public void setUp() {

        Agenda agenda = Agenda.createForEvent("Test Event");
        agendaRepository.persist(agenda);
        agendaId = agenda.id;
    }

    @Test
    public void testAddingSession() {

        SessionAddedResult sessionAddedResult = agendaService.addSession(1L,
                new SessionRecord(
                SESSION_TITLE,
                SESSION_SLUG,
                SESSION_DESCRIPTION,
                new ArrayList<PresenterRecord>(){{
                    add(new PresenterRecord(PRESENTER_EMAIL));
                }}
        ));

        AgendaUpdatedEvent agendaUpdatedEvent = sessionAddedResult.agendaUpdatedEvent();
        SessionAddedEvent sessionAddedEvent = sessionAddedResult.sessionAddedEvent();

        assertNotNull(sessionAddedEvent);
        assertNotNull(sessionAddedResult);

        Agenda agenda = agendaRepository.findById(agendaId);
        assertEquals(1, agenda.getEventSessions().size());
        assertEquals(SESSION_TITLE, agenda.getEventSessions().get(0).getTitle());
        assertEquals(SESSION_SLUG, agenda.getEventSessions().get(0).getSlug());
        assertEquals(SESSION_DESCRIPTION, agenda.getEventSessions().get(0).getDescription());
        assertEquals(PRESENTER_EMAIL, agenda.getEventSessions().get(0).getPresenters().get(0).getEmail());
    }
}
