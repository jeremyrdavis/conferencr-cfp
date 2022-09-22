package io.conferencr.agenda;

import io.conferencr.agenda.api.*;
import io.quarkus.test.junit.QuarkusTest;
import io.vertx.core.eventbus.EventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class AgendaServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgendaServiceTest.class);

    @Inject
    EventBus eventBus;

    private static final String SESSION_TITLE = "Session title";
    private static final String SESSION_SLUG = "Session slug";
    private static final String SESSION_DESCRIPTION = "Session description";
    private static final String PRESENTER_EMAIL = "foo@bar.com";

    @Inject
    AgendaService agendaService;

    @Inject
    AgendaRepository agendaRepository;

    Long agendaId;

    int sessionCount;

    boolean agendaUpdatedEventReceived;

    boolean sessionAddedEventReceived;

    @BeforeEach @Transactional
    public void setUp() {

        String eventName = "AgendaServiceTest Event";
        Agenda agenda = Agenda.createForEvent(eventName);
        agendaRepository.persist(agenda);
        agendaId = agenda.id;
//         = agendaRepository.find("eventName", eventName).firstResult();
        sessionCount = Optional.ofNullable(agenda.getEventSessions().size()).get();

        eventBus.consumer(DomainEvents.AGENDA_UPDATED)
                .handler(message -> {
                    LOGGER.info("message received: {}", message.body().toString());
                    agendaUpdatedEventReceived = true;
                });

        eventBus.consumer(DomainEvents.SESSION_ADDED)
                .handler(message -> {
                    LOGGER.info("message received: {}", message.body().toString());
                    sessionAddedEventReceived = true;
                });
    }

    @Test
    public void testAddingSession() {

        agendaService.addSession(
                new SessionRecord(
                SESSION_TITLE,
                SESSION_SLUG,
                SESSION_DESCRIPTION,
                new ArrayList<PresenterRecord>(){{
                    add(new PresenterRecord(PRESENTER_EMAIL));
                }}
        ));

        assertTrue(agendaUpdatedEventReceived);
        assertTrue(sessionAddedEventReceived);

        Agenda agenda = agendaRepository.findById(agendaId);
        assertEquals((sessionCount + 1), agenda.getEventSessions().size());
        assertTrue(agenda.getEventSessions().stream().map(eventSession -> {
            return eventSession.getTitle();
        }).collect(Collectors.toList()).contains(SESSION_TITLE));
        assertTrue(agenda.getEventSessions().stream().map(eventSession -> {
            return eventSession.getSlug();
        }).collect(Collectors.toList()).contains(SESSION_SLUG));
        assertTrue(agenda.getEventSessions().stream().map(eventSession -> {
            return eventSession.getDescription();
        }).collect(Collectors.toList()).contains(SESSION_DESCRIPTION));
        assertTrue(agenda.getEventSessions().stream().flatMap(eventSession -> {
            return eventSession.getPresenters().stream();
        }).map(presenter -> {
            return presenter.getEmail();
        }).collect(Collectors.toList()).contains(PRESENTER_EMAIL));
    }
}
