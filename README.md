# graphql-config-reload-reproducer

Shows the issue [#1591](https://github.com/smallrye/smallrye-graphql/issues/1591).

## Instructions

1. Install a [WildFly](https://www.wildfly.org/downloads/) (e.g. 26.1.2) with the [GraphQL Feature Pack](https://github.com/wildfly-extras/wildfly-graphql-feature-pack).
2. Build this project with `mvn`.
3. Deploy the `ROOT.war` to the WildFly.
4. Call `http :8080/graphql query='{hello}'` (or use `curl` if you don't like [httpie](https://httpie.io/cli)).
5. The error has a message `failed hello detail`.
6. Disable (with a leading `#`) the `mp.graphql.showErrorMessage` setting in `microprofile-config.properties`.
7. Rebuild and redeploy (without restarting).
8. If you redo the call, the message is still `failed hello detail`.
9. Restart the WildFly.
10. If you redo the call again, the message is now `System error`.

The response in step 10 is correct; that in step 8 is not.
