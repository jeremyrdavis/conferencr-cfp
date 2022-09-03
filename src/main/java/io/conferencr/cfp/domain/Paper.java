package io.conferencr.cfp.domain;

import io.conferencr.domain.Speaker;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

// Aggregate for Papers
@Entity
public class Paper extends PanacheEntity {

    private String title;

    private String slug;

    @Lob
    @Column(name = "ABSTRACT_BODY", length = 500)
    private String body;

    @ManyToOne
    private Speaker speaker;

    @OneToMany(mappedBy = "paper", fetch = FetchType.EAGER)
    private Collection<UpVote> votes;

    public Paper() {
    }

    public Paper(String title, String slug, String body, Speaker speaker) {
        this.body = body;
        this.slug = slug;
        this.speaker = speaker;
        this.title = title;
    }

    public void voteUp(UpVote upVote) {
        if (this.votes == null) {
            this.votes = new ArrayList<UpVote>(){{
                add(upVote);
            }};
        }else{
            this.votes.add(upVote);
        }
    }

    public int getVotes() {

        if (this.votes == null) {
            return 0;
        }else{
            return this.votes.size();
        }
    }

    public Optional<Collection<UpVote>> getAllVotes() {
        return Optional.ofNullable(this.votes);
    }

    public String getTitle() {

        return this.title;
    }

    public String getSlug() {
        return this.slug;
    }

    public String getBody() {
        return this.body;
    }

    public Speaker getSpeaker() {
        return this.speaker;
    }



    @Override
    public String toString() {
        return "Paper{" +
                "title='" + title + '\'' +
                ", slug='" + slug + '\'' +
                ", body='" + body + '\'' +
                ", speaker=" + speaker +
                ", votes=" + votes +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paper paper = (Paper) o;

        if (title != null ? !title.equals(paper.title) : paper.title != null) return false;
        if (slug != null ? !slug.equals(paper.slug) : paper.slug != null) return false;
        if (body != null ? !body.equals(paper.body) : paper.body != null) return false;
        if (speaker != null ? !speaker.equals(paper.speaker) : paper.speaker != null) return false;
        return votes != null ? votes.equals(paper.votes) : paper.votes == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (slug != null ? slug.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (speaker != null ? speaker.hashCode() : 0);
        result = 31 * result + (votes != null ? votes.hashCode() : 0);
        return result;
    }

}
