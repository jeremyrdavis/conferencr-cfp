package io.conferencr.attendees.api;

import java.util.List;

public interface AttendeeAPI {

    public AttendeeRecord registerAttendee(AttendeeRecord attendeeRecord);

    public List<AttendeeRecord> listAttendees();
}
