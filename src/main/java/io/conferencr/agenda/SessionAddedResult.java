package io.conferencr.agenda;

import io.conferencr.agenda.api.AgendaUpdatedEvent;
import io.conferencr.agenda.api.SessionAddedEvent;

record SessionAddedResult(AgendaUpdatedEvent agendaUpdatedEvent, SessionAddedEvent sessionAddedEvent) {
}
