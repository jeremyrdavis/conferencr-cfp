package io.conferencr.agenda;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Presenter extends PanacheEntity {

    private String email;

    public Presenter() {
    }

    protected Presenter(final String email) {

        this.email = email;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
