package io.conferencr;

import io.conferencr.agenda.api.AgendaUpdatedEvent;
import io.conferencr.agenda.api.PresenterRecord;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class ObjectMapperTest {


    @Test
    public void testAgendaUpdatedEvent() {

        String expectedResult = """
                {"sessionTitle":"Test title","presenters":[{"email":"daffy@disney.com"}]}
                """.strip();
        String result = JsonMapper.toJson(new AgendaUpdatedEvent("Test title", Collections.singletonList(new PresenterRecord("daffy@disney.com"))));
        System.out.println(expectedResult);
        System.out.println(result);
        assertNotNull(result);
        assertTrue(expectedResult.equals(result));
    }

}
