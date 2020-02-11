package fr.batigere.gateway.contactsmgmt;

import fr.batigere.gateway.contactsmgmt.endpoints.ContactsMgmtEndpoint;
import fr.batigere.gateway.rest.dtos.Contact;
import org.eclipse.microprofile.opentracing.Traced;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ContactsService {

    @Inject
    private ContactsMgmtEndpoint contactsMgmtEndpoint;

    @Traced
    public Contact getContactByUsername(String userName, String token){
        return this.contactMgmtToDto(this.contactsMgmtEndpoint.getContactByUsername(userName, token));
    }

    private Contact contactMgmtToDto(fr.batigere.gateway.contactsmgmt.dtos.Contact cont){
        Contact res = new Contact();
        res.setEmail(cont.getEmail());
        res.setFirstName(cont.getFirstName());
        res.setLastName(cont.getLastName());
        res.setSalutation(cont.getSalutation());
        res.setUserName(cont.getUserName());

        return res;
    }
}
