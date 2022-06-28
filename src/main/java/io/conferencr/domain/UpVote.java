package io.conferencr.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UpVote extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name="session_abstract_id", nullable=false)
    SessionAbstract sessionAbstract;

    @ManyToOne
    @JoinColumn(name="reviewer_id", nullable=false)
    Reviewer reviewer;

    protected UpVote(SessionAbstract sessionAbstract, Reviewer reviewer) {
        this.sessionAbstract = sessionAbstract;
        this.reviewer = reviewer;
    }

    protected UpVote() {

    }

    @Override
    public String toString() {
        return "UpVote{" +
                "sessionAbstract=" + sessionAbstract +
                ", reviewer=" + reviewer +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpVote upVote = (UpVote) o;

        if (sessionAbstract != null ? !sessionAbstract.equals(upVote.sessionAbstract) : upVote.sessionAbstract != null)
            return false;
        return reviewer != null ? reviewer.equals(upVote.reviewer) : upVote.reviewer == null;
    }

    @Override
    public int hashCode() {
        int result = sessionAbstract != null ? sessionAbstract.hashCode() : 0;
        result = 31 * result + (reviewer != null ? reviewer.hashCode() : 0);
        return result;
    }
}
