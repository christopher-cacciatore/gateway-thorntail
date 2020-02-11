package fr.batigere.gateway.rest;

import fr.batigere.gateway.casesmgmt.CasesService;
import fr.batigere.gateway.contactsmgmt.ContactsService;
import fr.batigere.gateway.rest.dtos.Case;
import fr.batigere.gateway.rest.dtos.Contact;
import io.swagger.annotations.Api;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import org.eclipse.microprofile.opentracing.Traced;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "gateway cases-mgmt", basePath = "/api/v2/cases")
@SwaggerDefinition(info = @Info(title = "Gateway pour la gestion des cases", version="1.0"))
@Traced
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
    public Case getCaseById(@PathParam("id") String id, @Context HttpServletRequest servletRequest) {
        String token = this.getTokenFromHttpServletRequest(servletRequest);
        Case res = this.casesService.getCaseById(id, token);
        res = this.findContactForCase(res, token);
        return res;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Case> getAllCases(@Context HttpServletRequest servletRequest) {
        String token = this.getTokenFromHttpServletRequest(servletRequest);
        return this.casesService.getAllCases(token)
                .stream()
                .map(c -> this.findContactForCase(c, token))
                .collect(Collectors.toList());
    }

    private Case findContactForCase(Case cse, String token) {
        if (cse != null) {
            Contact ctct = this.contactsService.getContactByUsername(cse.getCreatedBy().getUserName(), token);
            if (ctct != null) {
                cse.setCreatedBy(ctct);
            }
        }
        return cse;
    }

    private String getTokenFromHttpServletRequest(HttpServletRequest servletRequest){
        Principal userPrincipal = servletRequest.getUserPrincipal();
        KeycloakPrincipal<KeycloakSecurityContext> kcPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) userPrincipal;
        return "Bearer "+kcPrincipal.getKeycloakSecurityContext().getTokenString();
    }


}
