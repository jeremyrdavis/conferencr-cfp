package io.conferencr.agenda;

import io.conferencr.cfp.domain.Speaker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AgendaTest {

    @Test
    public void testUpdateAgenda() {

        Agenda agenda = Agenda.createForEvent("This conference");
        agenda.addEventSession(new EventSession(
                "This talk",
                "This slug",
                "This description",
                new ArrayList<>(2){{
                    add(new Presenter("foo@bar.com"));
                }}
        ));

        assertEquals(1, agenda.getEventSessions().size());
    }

    @Test
    public void testAddEventSession() {

        Agenda agenda = new Agenda("Test event");
        EventSession eventSession = new EventSession(
                "Test title",
                "Test slug",
                "Test description",
                Collections.singletonList(new Presenter("james.kirk@enterprise.mil"))
        );

        agenda.addEventSession(eventSession);
        assertEquals(1, agenda.getEventSessions().size());
        assertEquals(eventSession, agenda.getEventSessions().get(0));

    }
}
