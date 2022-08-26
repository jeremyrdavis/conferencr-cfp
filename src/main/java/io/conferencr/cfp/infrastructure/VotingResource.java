package io.conferencr.cfp.infrastructure;

import io.conferencr.cfp.domain.Paper;
import io.conferencr.cfp.domain.PaperRepository;
import io.conferencr.cfp.domain.valueobjects.UpVoteVO;
import org.slf4j.Logger;

import javax.inject.Inject;
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

    @Inject
    PaperRepository paperRepository;

    @POST@Transactional
    public Response upVote(final UpVoteVO upVoteJson) {

        LOGGER.debug("up vote received: {}", upVoteJson);

        Paper paper = paperRepository.upVote(upVoteJson);

        LOGGER.debug("Paper updated");

        return Response.ok().entity(paper).build();
    }

}
