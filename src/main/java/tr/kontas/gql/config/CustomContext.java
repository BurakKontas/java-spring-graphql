package tr.kontas.gql.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomContext {
    private final String userId;
    private final String role;
}
