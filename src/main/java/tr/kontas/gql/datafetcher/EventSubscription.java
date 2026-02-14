package tr.kontas.gql.datafetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsSubscription;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.context.DgsContext;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import tr.kontas.gql.bus.GlobalEventBus;
import tr.kontas.gql.config.CustomContext;
import tr.kontas.gql.model.Book;
import tr.kontas.gql.model.Event;
import tr.kontas.gql.model.EventType;

import java.util.List;

@DgsComponent
@RequiredArgsConstructor
public class EventSubscription {

    private final GlobalEventBus eventBus;

    @DgsSubscription
    public Flux<Event> events(@InputArgument List<EventType> types, DgsDataFetchingEnvironment dfe) {
        CustomContext context = DgsContext.getCustomContext(dfe);

        String userId = context != null ? context.getUserId() : null;

        return eventBus.stream()
                .filter(event ->
                        (userId == null || userId.equals(event.getUserId())) &&
                                (types == null || types.contains(event.getType()))
                )
                .map(e -> new Event(
                        e.getType(),
                        e.getUserId(),
                        e.getGroupId(),
                        e.getPayload()
                ));
    }

    @DgsSubscription
    public Flux<Book> bookAdded(@InputArgument String userId) {

        return eventBus.stream()
                .filter(e ->
                        e.getType() == EventType.BOOK_CREATED &&
                                (userId == null || userId.equals(e.getUserId()))
                )
                .map(e -> (Book) e.getPayload());
    }
}