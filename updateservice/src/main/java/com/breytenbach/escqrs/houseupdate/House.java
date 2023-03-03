package com.breytenbach.escqrs.houseupdate;


import com.breytenbach.escqrs.model.HousePurchaseCommand;
import com.breytenbach.escqrs.model.HousePurchaseEvent;
import com.breytenbach.escqrs.model.HouseSellCommand;
import com.breytenbach.escqrs.model.HouseSellEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.time.Instant;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate(cache = "houseCache")
public class House {
    @AggregateIdentifier
    String id;
    BigDecimal price;
    String type;
    BigDecimal sellPrice;
    @CommandHandler
    public House(HousePurchaseCommand command) {
            apply(HousePurchaseEvent.builder()
                .id(command.getId())
                .price(command.getPrice())
                .type(command.getType())
                .build());
    }

    public House() {

    }

    @CommandHandler
    public void handle(HouseSellCommand command) {
        apply(HouseSellEvent.builder()
                .id(command.getId())
                .price(command.getPrice())
                .time(Instant.now().toString())
                .build());
    }

    @EventSourcingHandler
    public void on(HousePurchaseEvent event) {
        id = event.getId();
        price = event.getPrice();
        type = event.getType();
    }

    @EventSourcingHandler
    public void on(HouseSellEvent event) {
        sellPrice = event.getPrice();
        type = event.getType();
    }

}
