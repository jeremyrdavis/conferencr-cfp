package io.conferencr.attendees.domain;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class AttendeeRepository implements PanacheRepository<Attendee> {
}
