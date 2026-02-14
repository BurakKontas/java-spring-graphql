package tr.kontas.gql.repository;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import tr.kontas.gql.model.Author;
import tr.kontas.gql.model.Book;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class BookRepository {

    private final List<Book> books = new CopyOnWriteArrayList<>();

    public BookRepository() {
        books.add(new Book("b1", "1984", "a2", OffsetDateTime.now()));
        books.add(new Book("b2", "Animal Farm", "a1", OffsetDateTime.now()));
        books.add(new Book("b3", "Crime and Punishment", "a2", OffsetDateTime.now()));
        books.add(new Book("b4", "The Brothers Karamazov", "a3", OffsetDateTime.now()));
        books.add(new Book("b5", "Harry Potter and the Philosopher's Stone", "a3", OffsetDateTime.now()));
        books.add(new Book("b6", "Harry Potter and the Chamber of Secrets", "a1", OffsetDateTime.now()));
    }

    public List<Book> findAll() {
        return books;
    }

    public void save(Book book) {
        books.add(book);
    }

    @SneakyThrows
    public Book findById(String id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst()
                .orElseThrow(() -> new Exception("Book not found"));
    }
}
