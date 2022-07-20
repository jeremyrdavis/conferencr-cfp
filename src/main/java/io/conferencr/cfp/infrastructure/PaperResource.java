package io.conferencr.cfp.infrastructure;

import io.conferencr.cfp.domain.Paper;
import io.conferencr.cfp.domain.valueobjects.PaperVO;
import io.conferencr.cfp.domain.PaperRepository;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Path("/papers")
@Produces(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON)
public class PaperResource {

    private static final Logger LOGGER = getLogger(PaperResource.class);

    @Inject
    PaperRepository paperRepository;

    @Inject
    PaperService paperService;

    @GET@Transactional
    public Response getAbstracts() {

        List<Paper> papers = paperRepository.listAll();
        return Response.ok().entity(papers).build();
    }
    @POST@Transactional
    public Response addPaper(final PaperVO paperRecord) {

        LOGGER.debug("Adding {}", paperRecord);

//        Paper paper = Paper.createFromSessionAbstractValueObject(sessionAbstractJson);
        Paper paper = paperService.createFromPaperVO(paperRecord);
        return Response.created(URI.create("/" + paper.id)).entity(paper).build();
    }
}
