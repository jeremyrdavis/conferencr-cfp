package io.conferencr.agenda;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class AgendaRepository implements PanacheRepository<Agenda> {
}
