package io.converencr.agenda.domain;

import io.converencr.agenda.api.Presenter;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.util.List;

@Entity
class Session extends PanacheEntity {

    List<Presenter> presenters;

    String title;

    String description;

    public Session() {
    }

    public Session(List<Presenter> presenters, String title, String description) {
        this.presenters = presenters;
        this.title = title;
        this.description = description;
    }
}
