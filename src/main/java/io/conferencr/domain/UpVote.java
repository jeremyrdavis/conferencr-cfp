package io.conferencr.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UpVote extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name="paper_id", nullable=false)
    Paper paper;

    @ManyToOne
    @JoinColumn(name="reviewer_id", nullable=false)
    Reviewer reviewer;

    protected UpVote(Paper paper, Reviewer reviewer) {
        this.paper = paper;
        this.reviewer = reviewer;
    }

    protected UpVote() {

    }

    @Override
    public String toString() {
        return "UpVote{" +
                "sessionAbstract=" + paper +
                ", reviewer=" + reviewer +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpVote upVote = (UpVote) o;

        if (paper != null ? !paper.equals(upVote.paper) : upVote.paper != null)
            return false;
        return reviewer != null ? reviewer.equals(upVote.reviewer) : upVote.reviewer == null;
    }

    @Override
    public int hashCode() {
        int result = paper != null ? paper.hashCode() : 0;
        result = 31 * result + (reviewer != null ? reviewer.hashCode() : 0);
        return result;
    }
}
