package io.converencr.agenda.api;

import java.util.List;

public record ConferenceSession(String title, String description, List<Presenter> presenters, SessionType sessionType) {
}
