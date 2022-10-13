package com.example.graphql.config.reload.reproducer;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class Hellos {
    @Query
    public String hello() {
        throw new FailedHelloException("failed hello detail");
    }
}

class FailedHelloException extends RuntimeException {
    public FailedHelloException(String detail) {super(detail);}
}
