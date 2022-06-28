package io.conferencr.infrastructure;

import io.conferencr.domain.Reviewer;
import io.conferencr.domain.ReviewerJSON;
import org.slf4j.Logger;

import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import static org.slf4j.LoggerFactory.getLogger;

@Path("/reviewers")
public class ReviewerResource {

    private static final Logger LOGGER = getLogger(ReviewerResource.class);

    @POST @Transactional
    public Response addReviewer(final ReviewerJSON reviewerJSON) {

        LOGGER.debug("reviewerJSON: {}", reviewerJSON);
        Reviewer reviewer = new Reviewer(reviewerJSON.email);
        reviewer.persist();
        return Response.ok().entity(reviewer).build();
    }
}
