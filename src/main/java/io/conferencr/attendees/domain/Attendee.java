package io.conferencr.attendees.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Attendee extends PanacheEntity {

    private String email;
    protected Attendee(final String email) {
        this.email = email;
    }

    protected Attendee() {
    }

    @Override
    public String toString() {
        return "Attendee{" +
                "email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendee attendee = (Attendee) o;
        return Objects.equals(email, attendee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    public String getEmail() {
        return email;
    }

    protected void setEmail(final String email) {
        this.email = email;
    }
}
