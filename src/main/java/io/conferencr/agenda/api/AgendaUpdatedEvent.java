package io.conferencr.agenda.api;

import io.conferencr.agenda.api.PresenterRecord;

import java.util.List;

public record AgendaUpdatedEvent(String sessionTitle, List<PresenterRecord> presenters) {
}
