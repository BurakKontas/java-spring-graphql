package tr.kontas.gql.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DomainEvent<T> {
    private EventType type;
    private String userId;
    private String groupId;
    private T payload;
}
