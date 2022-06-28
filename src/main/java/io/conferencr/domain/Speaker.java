package io.conferencr.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Speaker extends PanacheEntity {

    @Column(unique = true)
    private String email;

    private String firstName;

    private String lastName;

    public Speaker(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Speaker() {
    }

    public static Speaker findByEmail(String speakerEmail) {

        return find("email", speakerEmail).firstResult();
    }

    @Override
    public String toString() {
        return "Speaker{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Speaker speaker = (Speaker) o;

        if (email != null ? !email.equals(speaker.email) : speaker.email != null) return false;
        if (firstName != null ? !firstName.equals(speaker.firstName) : speaker.firstName != null) return false;
        return lastName != null ? lastName.equals(speaker.lastName) : speaker.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
