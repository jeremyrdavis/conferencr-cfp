package io.converencr.agenda.domain;

import io.conferencr.domain.Speaker;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
class Session extends PanacheEntity {

    @OneToMany
    private List<Speaker> speakers;

    private String title;

    private String description;

    protected Session() {
    }

    protected Session(List<Speaker> speakers, String title, String description) {
        this.speakers = speakers;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Session{" +
                "speakers=" + speakers +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        if (speakers != null ? !speakers.equals(session.speakers) : session.speakers != null) return false;
        if (title != null ? !title.equals(session.title) : session.title != null) return false;
        return description != null ? description.equals(session.description) : session.description == null;
    }

    @Override
    public int hashCode() {
        int result = speakers != null ? speakers.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    protected void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    protected void setDescription(String description) {
        this.description = description;
    }
}
