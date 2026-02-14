package tr.kontas.gql.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Event {
    private EventType type;
    private String userId;
    private String groupId;
    private Object payload;
}
