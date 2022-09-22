package io.conferencr.agenda;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.maven.wagon.events.SessionEvent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class AgendaTestAgendaPublished {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgendaTestAgendaPublished.class);

    static final String AGENDA_NAME  = "Unit Test Conference Agenda";

    static Long agendaId;

    @BeforeAll @Transactional
    public static void setUp() {

        Agenda agenda = new Agenda(AGENDA_NAME);
        agenda.persist();
        agendaId = agenda.id;
    }

    @Test
    public void testAgendaIsPublishedWhenSessionsAreFull() {

        Agenda agenda = Agenda.findById(agendaId);

        new Random().ints(8).forEach(i -> {
            EventSession eventSession = new EventSession(
                    String.format("Test session {}",  i),
                    String.format("Test session {} slug", i),
                    String.format("Test session {} description"),
                    new ArrayList<Presenter>(){{
                add(new Presenter("presenter" + i + "@some.co"));
            }});
            agenda.addEventSession(eventSession);
        });

        agenda.persist();

        assertTrue(agenda.isPublished());
    }
}
