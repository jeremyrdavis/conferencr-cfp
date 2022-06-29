package io.conferencr.infrastructure;

import io.conferencr.domain.Paper;
import io.conferencr.domain.SessionAbstract;
import io.conferencr.domain.valueobjects.SessionAbstractValueObject;
import org.slf4j.Logger;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Path("/papers")
@Produces(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON)
public class SessionAbstractResource {

    private static final Logger LOGGER = getLogger(SessionAbstractResource.class);

    @GET@Transactional
    public Response getAbstracts() {

        List<SessionAbstract> sessionAbstracts = SessionAbstract.listAll();
        return Response.ok().entity(sessionAbstracts).build();
    }
    @POST@Transactional
    public Response addAbstract(final SessionAbstractValueObject sessionAbstractJson) {

        LOGGER.debug("Adding {}", sessionAbstractJson);

        Paper paper = Paper.createFromSessionAbstractJSON(sessionAbstractJson);
        return Response.created(URI.create("/" + paper.getSessionAbstract().id)).entity(paper.getSessionAbstract()).build();
    }
}
