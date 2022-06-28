package io.conferencr.domain;

import javax.transaction.Transactional;

// Aggregate for Papers
public class Paper {

    private SessionAbstract sessionAbstract;

    // Assumes that Speaker is already persisted and can be retrieved by email
    @Transactional
    public static Paper createFromSessionAbstractJSON(final SessionAbstractJson sessionAbstractJson) {

        Speaker speaker = Speaker.findByEmail(sessionAbstractJson.speakerEmail);

        SessionAbstract sessionAbstract = new SessionAbstract(
                sessionAbstractJson.title,
                sessionAbstractJson.slug,
                sessionAbstractJson.body,
                speaker);

        sessionAbstract.persist();
        return new Paper(sessionAbstract);
    }

    private Paper(SessionAbstract sessionAbstract) {
        this.sessionAbstract = sessionAbstract;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "sessionAbstract=" + sessionAbstract +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paper paper = (Paper) o;

        return sessionAbstract != null ? sessionAbstract.equals(paper.sessionAbstract) : paper.sessionAbstract == null;
    }

    @Override
    public int hashCode() {
        return sessionAbstract != null ? sessionAbstract.hashCode() : 0;
    }

    public SessionAbstract getSessionAbstract() {
        return sessionAbstract;
    }
}
