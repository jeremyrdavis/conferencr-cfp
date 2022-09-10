package io.conferencr.agenda;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Agenda extends PanacheEntity {

    private String eventName;

    @OneToMany
    private List<EventSession> eventSessions;

    protected Agenda() {

    }

    protected Agenda(final String eventName) {

        this.eventName = eventName;
    }

    public static Agenda createForEvent(final String eventName) {

        return new Agenda(eventName);
    }

    public void addEventSession(EventSession eventSession) {
        if (this.eventSessions == null) {
            this.eventSessions = new ArrayList<>(){{
                add(eventSession);
            }};
        }else {
            this.eventSessions.add(eventSession);
        }
    }

    public String getEventName() {
        return eventName;
    }

    public List<EventSession> getEventSessions() {
        return eventSessions;
    }
}
