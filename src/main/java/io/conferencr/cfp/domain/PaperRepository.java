package io.conferencr.cfp.domain;

import io.conferencr.cfp.domain.valueobjects.UpVoteVO;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class PaperRepository implements PanacheRepository<Paper> {

    @Transactional
    public Paper upVote(UpVoteVO upVoteJson) {

        Paper paper = findById(upVoteJson.paperId());
        Reviewer reviewer = Reviewer.findById(upVoteJson.reviewerId());
        UpVote upVote = new UpVote(paper, reviewer);
        paper.voteUp(upVote);
        persistAndFlush(paper);
        return paper;
    }


}
