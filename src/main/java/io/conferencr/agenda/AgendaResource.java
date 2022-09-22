package io.conferencr.agenda;

import io.conferencr.agenda.api.SessionRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/agenda")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AgendaResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgendaResource.class);

    @Inject
    AgendaService agendaService;

    @POST
    public Response addSession(final SessionRecord sessionRecordToAdd) {

        LOGGER.debug("adding session: {}", sessionRecordToAdd);
        agendaService.addSession(sessionRecordToAdd);
        return Response.created(URI.create("/")).build();
    }
}
