package tr.kontas.gql.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import tr.kontas.gql.model.Book;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class BookRepository {

    private final List<Book> books = new CopyOnWriteArrayList<>();

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
