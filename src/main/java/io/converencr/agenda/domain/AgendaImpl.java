package io.converencr.agenda.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class AgendaImpl extends PanacheEntity implements Agenda {

    @OneToMany
    List<Session> sessions;

    public AgendaImpl(List<Session> sessions) {
        this.sessions = sessions;
    }

    public AgendaImpl() {
    }

    void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public List<Session> getSessions() {
        return sessions;
    }



}
