package io.conferencr.agenda;

import java.util.List;

public record SessionAddedEvent(String sessionTitle, String sessionSlug, String sessionDescription, List<PresenterRecord> presenters) {
}
