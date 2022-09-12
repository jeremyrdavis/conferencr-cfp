package io.conferencr.attendees.api;

import java.util.List;

public interface AttendeeAPI {

    public AttendeeRecord registerAttendee(AttendeeRecord attendeeRecord);

    List<AttendeeRecord> listAttendees();
}
