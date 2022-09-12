package io.conferencr.attendees.api;

public record AttendeeRecord(Long id, String email) {

    public AttendeeRecord(String email) {
        this(null, email);
    }
}
