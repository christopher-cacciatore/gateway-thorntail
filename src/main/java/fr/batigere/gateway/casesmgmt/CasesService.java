package fr.batigere.gateway.casesmgmt;


import fr.batigere.gateway.casesmgmt.endpoints.CasesMgmtEndpoint;
import fr.batigere.gateway.rest.dtos.Case;
import fr.batigere.gateway.rest.dtos.Contact;
import org.eclipse.microprofile.opentracing.Traced;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CasesService {

    @Inject
    private CasesMgmtEndpoint casesMgmtEndpoint;

    @Traced
    public Case getCaseById(String id) {
        fr.batigere.gateway.casesmgmt.dtos.Case case1 = casesMgmtEndpoint.getCaseById(id);
        return this.casemgmtToDto(case1);
    }

    @Traced
    public List<Case> getAllCases() {
        return casesMgmtEndpoint.getAllCases()
                .stream()
                .map(c -> this.casemgmtToDto(c))
                .collect(Collectors.toList());
    }

    private Case casemgmtToDto(fr.batigere.gateway.casesmgmt.dtos.Case case1){
        Case res = new Case();
        res.setId(case1.getId());
        res.setDescription(case1.getDescription());
        res.setTitle(case1.getTitle());

        Contact ctct = new Contact();
        ctct.setUserName(case1.getCreatedBy());

        res.setCreatedBy(ctct);

        return res;
    }
}
