package fr.batigere.gateway.rest;

import fr.batigere.gateway.casesmgmt.CasesService;
import fr.batigere.gateway.contactsmgmt.ContactsService;
import fr.batigere.gateway.rest.dtos.Case;
import fr.batigere.gateway.rest.dtos.Contact;
import io.swagger.annotations.Api;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "gateway cases-mgmt", basePath = "/api/v2/cases")
@SwaggerDefinition(info = @Info(title = "Gateway pour la gestion des cases", version="1.0"))
@ApplicationScoped
@Path("/cases")
public class CasesEndpoint {

    @Inject
    private CasesService casesService;

    @Inject
    private ContactsService contactsService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Case getCaseById(@PathParam("id") String id) {
        Case res = this.casesService.getCaseById(id);
        res = this.findContactForCase(res);
        return res;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Case> getAllCases() {
        return this.casesService.getAllCases()
                .stream()
                .map(c -> this.findContactForCase(c))
                .collect(Collectors.toList());
    }

    private Case findContactForCase(Case cse) {
        if (cse != null) {
            Contact ctct = this.contactsService.getContactByUsername(cse.getCreatedBy().getUserName());
            if (ctct != null) {
                cse.setCreatedBy(ctct);
            }
        }
        return cse;
    }
}
