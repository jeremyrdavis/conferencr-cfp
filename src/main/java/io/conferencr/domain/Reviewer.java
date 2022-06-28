package io.conferencr.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Reviewer extends PanacheEntity {

    String email;

    public Reviewer(String email) {
        this.email = email;
    }

    public Reviewer() {
    }

    public String getEmail() {
        return email;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Reviewer{" +
                "email='" + email + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reviewer reviewer = (Reviewer) o;

        return email != null ? email.equals(reviewer.email) : reviewer.email == null;
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }
}
