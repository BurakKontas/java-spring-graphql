package tr.kontas.gql.model;

import lombok.*;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String id;
    private String title;
    private String authorId;
    private OffsetDateTime createdAt;
}