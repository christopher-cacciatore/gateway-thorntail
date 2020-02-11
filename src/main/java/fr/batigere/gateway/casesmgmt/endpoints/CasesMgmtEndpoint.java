package fr.batigere.gateway.casesmgmt.endpoints;

import fr.batigere.gateway.casesmgmt.dtos.Case;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/cases")
public interface CasesMgmtEndpoint {
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Case> getAllCases(@HeaderParam("Authorization") String token);

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Case getCaseById(@PathParam("id") String id, @HeaderParam("Authorization") String token);
}
