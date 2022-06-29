package io.conferencr.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class ReviewerJson {

    public String email;

    public ReviewerJson(@JsonProperty("email") String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ReviewerJSON{" +
                "email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReviewerJson that = (ReviewerJson) o;

        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }
}
