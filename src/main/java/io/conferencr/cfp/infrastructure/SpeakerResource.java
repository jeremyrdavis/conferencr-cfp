package io.conferencr.cfp.infrastructure;

import io.conferencr.domain.Speaker;
import io.conferencr.cfp.domain.SpeakerRepository;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Path("/speakers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
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
