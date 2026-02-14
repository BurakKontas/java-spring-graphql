package tr.kontas.gql.datafetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import lombok.RequiredArgsConstructor;
import tr.kontas.gql.bus.GlobalEventBus;
import tr.kontas.gql.model.DomainEvent;
import tr.kontas.gql.model.EventType;
import tr.kontas.gql.model.Order;

import java.util.UUID;

@DgsComponent
@RequiredArgsConstructor
public class OrderMutation {

    private final GlobalEventBus eventBus;

    @DgsMutation
    public Order createOrder(@InputArgument String userId, @InputArgument Double amount) {

        Order order = new Order(UUID.randomUUID().toString(), amount);

        eventBus.publish(
                new DomainEvent<>(
                        EventType.ORDER_CREATED,
                        userId,
                        null,
                        order
                )
        );

        return order;
    }
}