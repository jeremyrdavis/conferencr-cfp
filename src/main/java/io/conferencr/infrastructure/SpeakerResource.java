package io.conferencr.infrastructure;

import io.conferencr.domain.Speaker;
import io.conferencr.domain.SpeakerRepository;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Path("/speakers")
public class SpeakerResource {

    private static final Logger LOGGER = getLogger(SpeakerResource.class);

    @Inject
    SpeakerRepository speakerRepository;

    @POST@Transactional
    public Response addSpeaker(final Speaker speaker) {

        speakerRepository.persist(speaker);
        return Response.created(URI.create("/" + speaker.getId())).entity(speaker).build();
    }

    @GET
    public Response getSpeakers() {

        List<Speaker> speakerList = speakerRepository.listAll();
        return Response.ok().entity(speakerList).build();
    }
}
