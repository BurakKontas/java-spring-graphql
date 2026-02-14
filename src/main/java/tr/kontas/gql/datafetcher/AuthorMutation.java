package tr.kontas.gql.datafetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import lombok.RequiredArgsConstructor;
import tr.kontas.gql.model.Author;
import tr.kontas.gql.repository.AuthorRepository;

@DgsComponent
@RequiredArgsConstructor
public class AuthorMutation {

    private final AuthorRepository repository;

    @DgsMutation
    public Author addAuthor(@InputArgument String id, @InputArgument String name) {
        Author author = new Author(id, name);
        repository.add(author);
        return author;
    }
}