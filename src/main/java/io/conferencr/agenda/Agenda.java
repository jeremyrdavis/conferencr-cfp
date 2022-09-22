package io.conferencr.agenda;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
class Agenda extends PanacheEntity {

    private String eventName;

    private boolean published;

    @OneToMany
    private List<EventSession> eventSessions;

    protected Agenda() {

    }

    protected Agenda(final String eventName) {

        this.eventName = eventName;
    }

    protected static Agenda createForEvent(final String eventName) {

        return new Agenda(eventName);
    }

    public void addEventSession(EventSession eventSession) {
        if(!this.published){
            if (this.eventSessions == null) {
                this.eventSessions = new ArrayList<>(){{
                    add(eventSession);
                }};
            }else {
                this.eventSessions.add(eventSession);
            }
        }
        if(this.eventSessions.size() == 8) this.published = true;
    }

    public String getEventName() {
        return eventName;
    }

    public List<EventSession> getEventSessions() {
        if (this.eventSessions == null) {
            this.eventSessions = new ArrayList<>();
        }
        return eventSessions;
    }

    public boolean isPublished() {
        return published;
    }
}
