package io.conferencr.attendee.domain;

import io.conferencr.attendees.api.AttendeeRecord;
import io.conferencr.attendees.domain.Attendee;
import io.conferencr.attendees.domain.AttendeeService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class AttendeeServiceTest {

    @Inject
    AttendeeService attendeeService;

    @Test
    public void testRegisteringAttendee() {

        AttendeeRecord attendeeRecord = new AttendeeRecord("pooh@ashdownforest.com");
        attendeeService.registerAttendee(attendeeRecord);

        assertEquals(1, Attendee.count());
        Attendee attendee = (Attendee) Attendee.listAll().get(0);
        assertEquals(attendeeRecord.email(), attendee.getEmail());
    }
}
