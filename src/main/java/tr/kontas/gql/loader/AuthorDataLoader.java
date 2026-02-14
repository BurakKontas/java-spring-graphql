package tr.kontas.gql.loader;

import com.netflix.graphql.dgs.DgsDataLoader;
import lombok.RequiredArgsConstructor;
import org.dataloader.BatchLoader;
import org.jspecify.annotations.NonNull;
import tr.kontas.gql.model.Author;
import tr.kontas.gql.repository.AuthorRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@DgsDataLoader(name = "authorLoader")
@RequiredArgsConstructor
public class AuthorDataLoader implements BatchLoader<String, Author> {

    private final AuthorRepository authorRepository;

    @NonNull
    @Override
    public CompletionStage<List<Author>> load(@NonNull List<String> authorIds) {

        return CompletableFuture.supplyAsync(() ->
                authorRepository.findByIds(authorIds)
        );
    }
}
