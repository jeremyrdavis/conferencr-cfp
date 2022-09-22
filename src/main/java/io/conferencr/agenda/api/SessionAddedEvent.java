package io.conferencr.agenda.api;

import io.conferencr.agenda.api.SessionRecord;

public record SessionAddedEvent(SessionRecord sessionRecord) {
}
