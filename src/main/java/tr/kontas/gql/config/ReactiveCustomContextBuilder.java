package tr.kontas.gql.config;

import com.netflix.graphql.dgs.reactive.DgsReactiveCustomContextBuilderWithRequest;
import graphql.GraphQLContext;
import lombok.SneakyThrows;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class ReactiveCustomContextBuilder
        implements DgsReactiveCustomContextBuilderWithRequest<CustomContext> {

    @Override
    public @NonNull Mono<CustomContext> build(
            @Nullable Map<String, ?> extensions,
            @Nullable HttpHeaders headers,
            @Nullable ServerRequest serverRequest) {

        String userId = headers != null ? headers.getFirst("x-user-id") : null;
        String role = headers != null ? headers.getFirst("x-user-role") : null;

        return Mono.just(
                new CustomContext(
                        userId != null ? userId : "anonymous",
                        role != null ? role : "peasant"
                )
        );
    }
}
