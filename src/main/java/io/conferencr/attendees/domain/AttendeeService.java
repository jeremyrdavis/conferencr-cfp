package io.conferencr.attendees.domain;

import io.conferencr.attendees.api.AttendeeAPI;
import io.conferencr.attendees.api.AttendeeRecord;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import static org.slf4j.LoggerFactory.getLogger;

@ApplicationScoped
public class AttendeeService implements AttendeeAPI {

    private static final Logger LOGGER = getLogger(AttendeeService.class);

    @Override @Transactional
    public void registerAttendee(AttendeeRecord attendeeRecord) {

        Attendee attendee = new Attendee(attendeeRecord.email());
        attendee.persist();
        LOGGER.debug("persisted: {}", attendee);
    }
}
