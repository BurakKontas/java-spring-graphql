package tr.kontas.gql.repository;

import org.springframework.stereotype.Component;
import tr.kontas.gql.model.Author;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class AuthorRepository {

    private final Map<String, Author> authors = new ConcurrentHashMap<>();

    public AuthorRepository() {
        authors.put("a1", new Author("a1", "George Orwell"));
        authors.put("a2", new Author("a2", "Fyodor Dostoevsky"));
        authors.put("a3", new Author("a3", "J.K. Rowling"));
    }

    public List<Author> findByIds(List<String> ids) {
        return ids.stream()
                .map(authors::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Optional<Author> findById(String id) {
        return Optional.ofNullable(authors.get(id));
    }

    public void add(Author author) {
        authors.put(author.getId(), author);
    }
}
