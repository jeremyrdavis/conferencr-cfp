package io.conferencr.infrastructure;

import io.conferencr.domain.SessionAbstract;
import io.conferencr.domain.SessionAbstractJson;
import io.conferencr.domain.Speaker;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.slf4j.Logger;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Path("/abstracts")
@Produces(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON)
public class SessionAbstractResource {

    private static final Logger LOGGER = getLogger(SessionAbstractResource.class);

    @GET@Transactional
    public Response getAbstracts() {

        List<SessionAbstract> sessionAbstracts = SessionAbstract.listAll();
        return Response.ok().entity(sessionAbstracts).build();
    }
    @POST@Transactional
    public Response addAbstract(final SessionAbstractJson sessionAbstractJson) {

        LOGGER.debug("Adding {}", sessionAbstractJson);

        PanacheQuery<Speaker> query = Speaker.find("email = ?1", sessionAbstractJson.speakerEmail);
        Speaker speaker = query.firstResult();
        SessionAbstract sessionAbstract = new SessionAbstract();
        sessionAbstract.setBody(sessionAbstractJson.body);
        sessionAbstract.setSlug(sessionAbstractJson.slug);
        sessionAbstract.setTitle(sessionAbstractJson.title);
        sessionAbstract.setSpeaker(speaker);
        sessionAbstract.persist();
        return Response.created(URI.create("/" + sessionAbstract.id)).entity(sessionAbstract).build();
    }
}
