package fr.batigere.gateway.contactsmgmt.endpoints;

import io.opentracing.contrib.jaxrs2.client.ClientTracingFeature;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class ContactsMgmtEndpointProducer {

    @Inject
    @ConfigProperty(name = "contacts.mgmt.base-url")
    private String contactsMgmtUrl;

    @Produces
    @RequestScoped
    public ContactsMgmtEndpoint buildContactsMgmtEndpoint(){
        ResteasyClient client = (ResteasyClient) ResteasyClientBuilder.newClient();
        client.register(ClientTracingFeature.class);
        ResteasyWebTarget target = client.target(this.contactsMgmtUrl);
        return target.proxy(ContactsMgmtEndpoint.class);
    }
}
