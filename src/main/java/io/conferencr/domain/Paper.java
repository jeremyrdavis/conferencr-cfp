package io.conferencr.domain;

// Aggregate for Papers
public class Paper {

    public Paper createFromSessionAbstractJSON(final SessionAbstractJson sessionAbstractJson) {

        Speaker speaker = Speaker.findByEmail(sessionAbstractJson.speakerEmail);

        SessionAbstract sessionAbstract = new SessionAbstract(
                sessionAbstractJson.title,
                sessionAbstractJson.slug,
                sessionAbstractJson.body,
                speaker);

        sessionAbstract.persist();
        return null;
    }
}
