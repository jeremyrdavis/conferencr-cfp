package io.conferencr.attendees.infrastructure;

import io.conferencr.attendees.api.AttendeeAPI;
import io.conferencr.attendees.api.AttendeeRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/attendees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AttendeeResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttendeeResource.class);
    @Inject
    AttendeeAPI attendeeAPI;

    @POST
    public Response registerAttendee(final AttendeeRecord attendeeRecord) {

        AttendeeRecord registeredAttendee = attendeeAPI.registerAttendee(attendeeRecord);
        LOGGER.debug("registered attendee: {}", attendeeRecord);
        return Response.created(URI.create("/" + registeredAttendee.id())).entity(registeredAttendee).build();
    }

    @GET
    public Response allAttendees() {

        return Response.ok().entity(attendeeAPI.listAttendees()).build();
    }
}
