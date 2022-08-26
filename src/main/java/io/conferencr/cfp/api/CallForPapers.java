package io.conferencr.cfp.api;

import io.conferencr.cfp.domain.valueobjects.PaperVO;
import io.conferencr.cfp.domain.valueobjects.ReviewerVO;
import io.conferencr.cfp.domain.valueobjects.SpeakerVO;
import io.conferencr.cfp.domain.valueobjects.UpVoteVO;

public interface CallForPapers {

    public void createSpeakerProfile(SpeakerVO speakerVO);

    public void updateSpeakerProfile(SpeakerVO speakerVO);

    public void submitPaper(PaperVO paperVO);

    public void updatePaper(PaperVO paperVO);

    public void addReviewer(ReviewerVO reviewerVO);

    public void voteUp(UpVoteVO upVoteVO);
}
