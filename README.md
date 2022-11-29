# graphql-config-reload-reproducer

Shows the issue [#1591](https://github.com/smallrye/smallrye-graphql/issues/1591).

## Instructions

1. Install a [WildFly](https://www.wildfly.org/downloads/) (e.g. 26.1.2) with the [GraphQL Feature Pack](https://github.com/wildfly-extras/wildfly-graphql-feature-pack).
2. Build this project with `mvn`.
3. Deploy the `ROOT.war` to the WildFly.
4. Call `http :8080/graphql query='{hello}'` (or use `curl` if you still not on [httpie](https://httpie.io/cli)).
5. The error has a message `failed hello detail`.
6. Disable (with a leading `#`) the `mp.graphql.showErrorMessage` setting in `microprofile-config.properties`, and change the `failed.hello.message` setting to `hello again`.
7. Rebuild and redeploy (without restarting).
8. Redo the GraphQL call, the message is now also `hello again`, but it should be `System error`, as the exception message should not be shown anymore.
9. Restart the WildFly.
10. If you redo the call again, the message is now `System error`.

It works with the MP REST Client:
1. Call `http ':8080/indirect'`. It returns `ho, Jane`.
2. Change the `api/mp-rest/uri` config to something invalid.
3. Rebuild and redeploy (without restarting)
4. Redo the REST call and it fails, as the config for that endpoint is now invalid.
