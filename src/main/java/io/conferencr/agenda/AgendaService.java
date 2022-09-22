package io.conferencr.agenda;

import io.conferencr.JsonMapper;
import io.conferencr.agenda.api.*;
import io.vertx.core.eventbus.EventBus;
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

    @Inject
    EventBus eventBus;

    @Transactional
    void addSession(SessionRecord sessionRecord) {

        LOGGER.debug("adding: {} for Agenda: {}", sessionRecord);

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

        Agenda agenda = agendaRepository.listAll().get(0);
        agenda.addEventSession(eventSession);
        agenda.persist();

        AgendaUpdatedEvent agendaUpdatedEvent = new AgendaUpdatedEvent(
                sessionRecord.title(),
                sessionRecord.presenters()
        );

        SessionAddedEvent sessionAddedEvent = new SessionAddedEvent(sessionRecord);

        eventBus.publish(DomainEvents.AGENDA_UPDATED, JsonMapper.toJson(agendaUpdatedEvent));
        eventBus.publish(DomainEvents.SESSION_ADDED, JsonMapper.toJson(sessionAddedEvent));
    }

    private List<Presenter> hydratePresenters(final List<PresenterRecord> presenterRecords) {

        List<Presenter> presenters = new ArrayList<>(presenterRecords.size());
        presenterRecords.forEach(presenterRecord -> {
            Presenter presenter = new Presenter(presenterRecord.email());
            LOGGER.debug("created {}", presenter);
            presenters.add(presenter);
        });
        return presenters;
    }
}
