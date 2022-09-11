package io.conferencr.agenda;

import java.util.List;

public record AgendaUpdatedEvent(String sessionTitle, List<PresenterRecord> presenters) {
}
