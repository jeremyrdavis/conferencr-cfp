package io.conferencr.agenda;

import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@ApplicationScoped
public class AgendaService {

    private static final Logger LOGGER = getLogger(AgendaService.class);

    @Inject
    AgendaRepository agendaRepository;

    @Transactional
    public SessionAddedResult addSession(Long id, SessionRecord sessionRecord) {

        LOGGER.debug("adding: {} for Agenda: {}", sessionRecord, id);

        List<Presenter> presenters = hydratePresenters(sessionRecord.presenters());
        presenters.forEach(presenter -> {
            LOGGER.debug("hydrated: {}", presenter);
        });
        presenters = presenters.stream().map(presenter -> {
            presenter.persist();
            return presenter;
        }).collect(Collectors.toList());
        presenters.forEach(presenter -> {
            LOGGER.debug("persisted: {}", presenter);
        });

        EventSession eventSession = new EventSession(
                sessionRecord.title(),
                sessionRecord.slug(),
                sessionRecord.description(),
                presenters);

        eventSession.persist();

        Agenda agenda = agendaRepository.findById(id);
        agenda.addEventSession(eventSession);
        agenda.persist();

        AgendaUpdatedEvent agendaUpdatedEvent = new AgendaUpdatedEvent(
                sessionRecord.title(),
                sessionRecord.presenters()
        );

        SessionAddedEvent sessionAddedEvent = new SessionAddedEvent(sessionRecord);

        return new SessionAddedResult(agendaUpdatedEvent, sessionAddedEvent);
    }

    private List<Presenter> hydratePresenters(final List<PresenterRecord> presenterRecords) {

        List<Presenter> presenters = new ArrayList<>(presenterRecords.size());
        presenterRecords.forEach(presenterRecord -> {
            Presenter presenter = new Presenter(presenterRecord.email());
            LOGGER.debug("created {}", presenter);
            presenters.add(presenter);
        });
//        List<Presenter> presenters = presenterRecords.stream()
//                .map(presenterRecord -> {
//                    return new Presenter(presenterRecord.email());
//                }).collect(Collectors.toList());
//        LOGGER.debug("hydrated: {}", presenters);
        return presenters;
    }
}
