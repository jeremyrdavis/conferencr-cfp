package io.conferencr.cfp.domain;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SpeakerRepository implements PanacheRepository<Speaker>  {

    public Speaker findByEmail(String speakerEmail) {

        return find("email", speakerEmail).firstResult();
    }
}
