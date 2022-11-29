package com.example.graphql.config.reload.reproducer;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.io.Closeable;

@Path("/")
@GraphQLApi
public class Hellos {
    @Inject @ConfigProperty(name = "failed.hello.message")
    String failedHelloMessage;

    @Inject Api api;

    @Query
    @Counted
    public String hello() {throw new FailedHelloException(failedHelloMessage);}

    @GET @Path("/indirect")
    public String indirect() {return api.hi("Jane");}

    @RegisterRestClient(configKey = "api")
    public interface Api extends Closeable {
        @GET @Path("/hi")
        String hi(@QueryParam("whom") String whom);
    }

    @GET @Path("/hi")
    public String hi(@QueryParam("whom") String whom) {return "ho, " + whom;}
}

class FailedHelloException extends RuntimeException {
    public FailedHelloException(String detail) {super(detail);}
}
