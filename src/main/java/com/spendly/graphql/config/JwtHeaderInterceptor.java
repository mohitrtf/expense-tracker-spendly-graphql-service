package com.spendly.graphql.config;

import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class JwtHeaderInterceptor implements WebGraphQlInterceptor {

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        // Get Authorization header from incoming HTTP request
        String authHeader = request.getHeaders().getFirst("Authorization");

        if (authHeader != null) {
            // Put JWT into GraphQL context for controllers to use
            request.configureExecutionInput((executionInput, builder) ->
                    builder.graphQLContext(context -> context.put("Authorization", authHeader)).build()
            );
        }

        return chain.next(request);
    }
}