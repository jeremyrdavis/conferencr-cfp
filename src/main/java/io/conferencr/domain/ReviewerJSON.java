package io.conferencr.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class ReviewerJSON {

    public String email;

    public ReviewerJSON(@JsonProperty("email") String email) {
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

        ReviewerJSON that = (ReviewerJSON) o;

        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }
}
