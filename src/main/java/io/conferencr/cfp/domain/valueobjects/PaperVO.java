package io.conferencr.cfp.domain.valueobjects;

public record PaperVO(
        String title,
        String slug,
        String body,
        String speakerEmail) {}
