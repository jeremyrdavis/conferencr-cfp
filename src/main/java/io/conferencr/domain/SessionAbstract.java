package io.conferencr.domain;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class SessionAbstract extends PanacheEntity {

    private String title;

    private String slug;

    @Lob @Column(name = "ABSTRACT_BODY", length = 500)
    private String body;

    @ManyToOne
    private Speaker speaker;

    @OneToMany(mappedBy = "sessionAbstract", fetch = FetchType.EAGER)
    private Collection<UpVote> votes;

    public SessionAbstract(String title, String slug, String body, Speaker speaker) {
        this.title = title;
        this.slug = slug;
        this.body = body;
        this.speaker = speaker;
    }

    public SessionAbstract(String title, String slug, String body) {
        this.title = title;
        this.slug = slug;
        this.body = body;
    }

    public SessionAbstract() {
    }

    public void addUpVote(UpVote upVote) {
        if (this.votes == null) {
            this.votes = new ArrayList<>(1);
        }
        this.votes.add(upVote);
    }

    @Override
    public String toString() {
        return "SessionAbstract{" +
                "title='" + title + '\'' +
                ", slug='" + slug + '\'' +
                ", body='" + body + '\'' +
                ", speaker=" + speaker +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionAbstract that = (SessionAbstract) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (slug != null ? !slug.equals(that.slug) : that.slug != null) return false;
        if (body != null ? !body.equals(that.body) : that.body != null) return false;
        return speaker != null ? speaker.equals(that.speaker) : that.speaker == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (slug != null ? slug.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (speaker != null ? speaker.hashCode() : 0);
        return result;
    }

    public String getTitle() {
        return title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    protected void setSlug(String slug) {
        this.slug = slug;
    }

    public String getBody() {
        return body;
    }

    protected void setBody(String body) {
        this.body = body;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    protected void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public Collection<UpVote> getVotes() {
        return votes;
    }

    protected void setVotes(Collection<UpVote> votes) {
        this.votes = votes;
    }

}
