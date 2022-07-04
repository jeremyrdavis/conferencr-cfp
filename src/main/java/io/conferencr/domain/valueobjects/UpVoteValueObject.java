package io.conferencr.domain.valueobjects;

public record UpVoteValueObject(
        Long paperId,
        Long reviewerId
) {}
