package io.conferencr.domain;

public final class UpVoteJson {

    public Long sessionAbstractId;

    public Long reviewerId;

    public UpVoteJson(Long sessionAbstractId, Long reviewerId) {

        this.sessionAbstractId = sessionAbstractId;
        this.reviewerId = reviewerId;
    }

    @Override
    public String toString() {
        return "UpVote{" +
                "sessionAbstractId=" + sessionAbstractId +
                ", reviewerId=" + reviewerId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpVoteJson upVoteJson = (UpVoteJson) o;

        if (sessionAbstractId != null ? !sessionAbstractId.equals(upVoteJson.sessionAbstractId) : upVoteJson.sessionAbstractId != null)
            return false;
        return reviewerId != null ? reviewerId.equals(upVoteJson.reviewerId) : upVoteJson.reviewerId == null;
    }

    @Override
    public int hashCode() {
        int result = sessionAbstractId != null ? sessionAbstractId.hashCode() : 0;
        result = 31 * result + (reviewerId != null ? reviewerId.hashCode() : 0);
        return result;
    }
}
