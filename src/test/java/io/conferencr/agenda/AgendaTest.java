package io.conferencr.agenda;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
