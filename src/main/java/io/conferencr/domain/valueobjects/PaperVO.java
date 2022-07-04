package io.conferencr.domain.valueobjects;

public record PaperVO(
        String title,
        String slug,
        String body,
        String speakerEmail) {}
