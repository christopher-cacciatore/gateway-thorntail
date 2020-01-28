package fr.batigere.gateway.casesmgmt.endpoints;

import io.opentracing.contrib.jaxrs2.client.ClientTracingFeature;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class CasesMgmtEndpointProducer {

    @Inject
    @ConfigProperty(name = "cases.mgmt.base-url")
    private String casesMgmtUrl;

    @Produces
    @RequestScoped
    public CasesMgmtEndpoint buildCasesMgmtEndpoint(){
        ResteasyClient client = (ResteasyClient) ResteasyClientBuilder.newClient();
        client.register(ClientTracingFeature.class);
        ResteasyWebTarget target = client.target(this.casesMgmtUrl);
        return target.proxy(CasesMgmtEndpoint.class);
    }
}
