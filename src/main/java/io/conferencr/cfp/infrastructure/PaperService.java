package io.conferencr.cfp.infrastructure;

import io.conferencr.cfp.domain.Paper;
import io.conferencr.cfp.domain.PaperRepository;
import io.conferencr.domain.Speaker;
import io.conferencr.cfp.domain.SpeakerRepository;
import io.conferencr.cfp.domain.valueobjects.PaperVO;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.slf4j.LoggerFactory.getLogger;

@ApplicationScoped
public class PaperService {

    private static final Logger LOGGER = getLogger(PaperService.class);

    @Inject
    PaperRepository paperRepository;

    @Inject
    SpeakerRepository speakerRepository;

    @Transactional
    public Paper createFromPaperVO(final PaperVO paperVO) {

        Speaker speaker = speakerRepository.findByEmail(paperVO.speakerEmail());

        Paper paper = new Paper(
                paperVO.title(),
                paperVO.slug(),
                paperVO.body(),
                speaker);

        paperRepository.persist(paper);
        LOGGER.debug("paper persisted: {}", paper);

        return paper;
    }
}
