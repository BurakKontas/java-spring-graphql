package tr.kontas.gql.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DomainEvent<T> {
    private final EventType type;
    private final String userId;
    private final String groupId;
    private final T payload;
}
