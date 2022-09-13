package io.conferencr.attendees.domain;

import io.conferencr.attendees.api.AttendeeAPI;
import io.conferencr.attendees.api.AttendeeRecord;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@ApplicationScoped
public class AttendeeService implements AttendeeAPI {

    private static final Logger LOGGER = getLogger(AttendeeService.class);

    @Inject
    AttendeeRepository attendeeRepository;

    @Override @Transactional
    public AttendeeRecord registerAttendee(AttendeeRecord attendeeRecord) {

        Attendee attendee = new Attendee(attendeeRecord.email());
        attendee.persist();
        LOGGER.debug("persisted: {}", attendee);
        return new AttendeeRecord(attendee.id, attendee.email);
    }

    @Override
    public List<AttendeeRecord> listAttendees() {
        return attendeeRepository.listAll().stream().map(attendee -> {
            return new AttendeeRecord(attendee.id, attendee.email);
        }).collect(Collectors.toList());
    }
}
