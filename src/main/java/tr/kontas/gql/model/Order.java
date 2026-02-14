package tr.kontas.gql.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private Double amount;
}
