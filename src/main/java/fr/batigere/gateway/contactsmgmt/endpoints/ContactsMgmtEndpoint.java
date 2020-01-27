package fr.batigere.gateway.contactsmgmt.endpoints;

import fr.batigere.gateway.contactsmgmt.dtos.Contact;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;

@Path("/contacts")
public interface ContactsMgmtEndpoint {

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Contact getContactByUsername(@PathParam("username") String userName);
}
