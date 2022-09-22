package io.conferencr.agenda.api;

import io.conferencr.agenda.api.PresenterRecord;

import java.util.List;

public record SessionRecord(String title, String slug, String description, List<PresenterRecord> presenters) {
}
