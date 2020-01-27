package fr.batigere.gateway.casesmgmt.endpoints;

import fr.batigere.gateway.casesmgmt.dtos.Case;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/cases")
public interface CasesMgmtEndpoint {
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Case> getAllCases();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Case getCaseById(@PathParam("id") String id);
}
