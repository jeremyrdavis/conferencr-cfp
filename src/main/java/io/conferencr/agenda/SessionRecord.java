package io.conferencr.agenda;

import java.util.List;

public record SessionRecord(String title, String slug, String description, List<PresenterRecord> presenters) {
}
