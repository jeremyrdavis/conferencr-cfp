package io.conferencer.domain;

public final class SessionAbstractJson {

    public String title;

    public String slug;

    public String body;

    public String speakerEmail;

    public SessionAbstractJson(String title, String slug, String body, String speakerEmail) {
        this.title = title;
        this.slug = slug;
        this.body = body;
        this.speakerEmail = speakerEmail;
    }

    @Override
    public String toString() {
        return "SpeakerJson{" +
                "title='" + title + '\'' +
                ", slug='" + slug + '\'' +
                ", body='" + body + '\'' +
                ", speakerEmail='" + speakerEmail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionAbstractJson that = (SessionAbstractJson) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (slug != null ? !slug.equals(that.slug) : that.slug != null) return false;
        if (body != null ? !body.equals(that.body) : that.body != null) return false;
        return speakerEmail != null ? speakerEmail.equals(that.speakerEmail) : that.speakerEmail == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (slug != null ? slug.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (speakerEmail != null ? speakerEmail.hashCode() : 0);
        return result;
    }
}
