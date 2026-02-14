package tr.kontas.gql.datafetcher;

import com.netflix.graphql.dgs.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.dataloader.DataLoader;
import tr.kontas.gql.model.Author;
import tr.kontas.gql.model.Book;
import tr.kontas.gql.repository.BookRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@DgsComponent
@RequiredArgsConstructor
public class BookQuery {

    private final BookRepository repository;

    @DgsQuery
    public List<Book> books() {
        return repository.findAll();
    }

    @DgsEntityFetcher(name = "Book")
    public Book book(Map<String, Object> values) {
        String id = values.get("id").toString();
        return repository.findById(id);
    }

    @SneakyThrows
    @DgsData(parentType = "Book", field = "author")
    public CompletableFuture<Author> author(DgsDataFetchingEnvironment dfe) {

        Book book = dfe.getSource();

        if(book == null) throw new Exception("Book is null");

        DataLoader<String, Author> dataLoader =
                dfe.getDataLoader("authorLoader");

        if(dataLoader == null) throw new Exception("DataLoader is null");

        return dataLoader.load(book.getAuthorId());
    }
}
