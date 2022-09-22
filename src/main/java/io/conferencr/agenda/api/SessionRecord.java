package io.conferencr.agenda.api;

import io.conferencr.agenda.api.PresenterRecord;

import java.util.List;

public record SessionRecord(Long id, String title, String slug, String description, List<PresenterRecord> presenters) {
}
