package io.conferencr.cfp.domain.valueobjects;

public record UpVoteValueObject(
        Long paperId,
        Long reviewerId
) {}
