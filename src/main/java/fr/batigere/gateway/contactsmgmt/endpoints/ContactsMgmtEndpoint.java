package fr.batigere.gateway.contactsmgmt.endpoints;

import fr.batigere.gateway.contactsmgmt.dtos.Contact;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;

@Path("/contacts")
public interface ContactsMgmtEndpoint {

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Contact getContactByUsername(@PathParam("username") String userName, @HeaderParam("Authorization") String token);
}
