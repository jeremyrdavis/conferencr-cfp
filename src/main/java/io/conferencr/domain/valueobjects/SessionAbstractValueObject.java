package io.conferencr.domain.valueobjects;

public record SessionAbstractValueObject(
        String title,
        String slug,
        String body,
        String speakerEmail) {}
