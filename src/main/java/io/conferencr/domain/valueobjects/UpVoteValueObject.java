package io.conferencr.domain.valueobjects;

public final class UpVoteValueObject {

    public Long sessionAbstractId;

    public Long reviewerId;

    public UpVoteValueObject(Long sessionAbstractId, Long reviewerId) {

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

        UpVoteValueObject upVoteJson = (UpVoteValueObject) o;

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
