package tr.kontas.gql.config;

import com.netflix.graphql.dgs.context.DgsReactiveCustomContextBuilder;
import graphql.GraphQLContext;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ReactiveCustomContextBuilder
        implements DgsReactiveCustomContextBuilder<CustomContext> {

    @Override
    public Mono<CustomContext> build(
            GraphQLContext graphQLContext,
            ServerWebExchange exchange) {

        // HTTP header'dan al (Query/Mutation için)
        String userId = exchange.getRequest()
                .getHeaders()
                .getFirst("x-user-id");

        if (userId == null) {
            userId = "anonymous";
        }

        return Mono.just(new CustomContext(userId));
    }
}
