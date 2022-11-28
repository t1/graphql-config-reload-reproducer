package com.example.graphql.config.reload.reproducer;

import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class Hellos {
    @Inject @ConfigProperty(name = "failed.hello.message")
    String failedHelloMessage;

    @Query
    public String hello() {
        throw new FailedHelloException(failedHelloMessage);
    }
}

class FailedHelloException extends RuntimeException {
    public FailedHelloException(String detail) {super(detail);}
}
