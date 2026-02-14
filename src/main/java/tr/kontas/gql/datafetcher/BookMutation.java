package tr.kontas.gql.datafetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import lombok.RequiredArgsConstructor;
import tr.kontas.gql.bus.GlobalEventBus;
import tr.kontas.gql.model.Book;
import tr.kontas.gql.model.DomainEvent;
import tr.kontas.gql.model.EventType;
import tr.kontas.gql.repository.BookRepository;

import java.time.OffsetDateTime;
import java.util.UUID;

@DgsComponent
@RequiredArgsConstructor
public class BookMutation {

    private final BookRepository repository;
    private final GlobalEventBus eventBus;

    @DgsMutation
    public Book addBook(@InputArgument String userId, @InputArgument String title, @InputArgument String authorId) {

        Book book = new Book(UUID.randomUUID().toString(), title, authorId, OffsetDateTime.now());
        repository.save(book);

        eventBus.publish(
                new DomainEvent<>(
                        EventType.BOOK_CREATED,
                        userId,
                        null,
                        book
                )
        );

        return book;
    }
}