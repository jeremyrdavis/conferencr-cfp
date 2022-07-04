package io.conferencr.infrastructure;

import io.conferencr.domain.Paper;
import io.conferencr.domain.PaperRepository;
import io.conferencr.domain.Speaker;
import io.conferencr.domain.SpeakerRepository;
import io.conferencr.domain.valueobjects.PaperVO;
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
