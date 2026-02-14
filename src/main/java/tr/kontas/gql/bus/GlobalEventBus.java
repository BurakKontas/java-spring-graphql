package tr.kontas.gql.bus;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import tr.kontas.gql.model.DomainEvent;

@Component
public class GlobalEventBus {

    private final Sinks.Many<DomainEvent<?>> sink =
            Sinks.many()
                    .multicast()
                    .onBackpressureBuffer();

    public void publish(DomainEvent<?> event) {
        sink.emitNext(event, Sinks.EmitFailureHandler.FAIL_FAST);
    }

    public Flux<DomainEvent<?>> stream() {
        return sink.asFlux();
    }
}
