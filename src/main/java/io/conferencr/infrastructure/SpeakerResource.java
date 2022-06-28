package io.conferencr.infrastructure;

import io.conferencr.domain.Speaker;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/speakers")
public class SpeakerResource {

    @POST@Transactional
    public Response addSpeaker(final Speaker speaker) {

        speaker.persist();
        return Response.created(URI.create("/" + speaker.id)).entity(speaker).build();
    }

    @GET
    public Response getSpeakers() {

        List<Speaker> speakerList = Speaker.listAll();
        return Response.ok().entity(speakerList).build();
    }
}
