package tr.kontas.gql.bus;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import tr.kontas.gql.model.DomainEvent;

@Slf4j
@Component
public class GlobalEventBus {

    private final Sinks.Many<DomainEvent<?>> sink =
            Sinks.many().multicast().onBackpressureBuffer();

    public void publish(DomainEvent<?> event) {
        sink.emitNext(event, Sinks.EmitFailureHandler.FAIL_FAST);
    }

    public Flux<DomainEvent<?>> stream() {
        return sink.asFlux()
                .doOnSubscribe(s -> log.atInfo().log("Subscriber connected " + s))
                .doOnCancel(() -> log.atInfo().log("Subscriber cancelled"))
                .doOnError(err -> log.atError().log("Subscriber error: " + err.getMessage()))
                .doOnComplete(() -> log.atInfo().log("Stream completed"))
                .doFinally(signal -> log.atInfo().log("Subscription closed reason: " + signal));
    }
}