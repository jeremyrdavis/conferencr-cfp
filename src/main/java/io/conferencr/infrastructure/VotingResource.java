package io.conferencr.infrastructure;

import io.conferencr.domain.SessionAbstract;
import io.conferencr.domain.UpVoteJson;
import org.slf4j.Logger;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.slf4j.LoggerFactory.getLogger;

@Path("/vote") @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
public class VotingResource {

    private static final Logger LOGGER = getLogger(VotingResource.class);

    @POST@Transactional
    public Response upVote(final UpVoteJson upVoteJson) {

        LOGGER.debug("up vote received: {}", upVoteJson);

        SessionAbstract sessionAbstract = SessionAbstract.findById(upVoteJson.sessionAbstractId);
        sessionAbstract.upVote();
        sessionAbstract.persist();

        LOGGER.debug("session abstract updated");

        return Response.ok().entity(sessionAbstract).build();
    }

}
