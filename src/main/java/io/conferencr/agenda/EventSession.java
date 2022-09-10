package io.conferencr.agenda;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class EventSession extends PanacheEntity {

    private String title;

    private String slug;

    private String description;

    @OneToMany
    private List<Presenter> presenters;

    protected EventSession() {
    }

    protected EventSession(String title, String slug, String description, List<Presenter> presenters) {
        this.title = title;
        this.slug = slug;
        this.description = description;
        this.presenters = presenters;
    }
}
