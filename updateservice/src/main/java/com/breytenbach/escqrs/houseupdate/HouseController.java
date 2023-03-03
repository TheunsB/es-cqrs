package com.breytenbach.escqrs.houseupdate;

import com.breytenbach.escqrs.model.HousePurchaseCommand;
import com.breytenbach.escqrs.model.HouseSellCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/house")
public class HouseController {
    private final CommandGateway commandGateway;

    public HouseController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping()
    Mono<String> registerHouse(@RequestBody HousePurchasePayload payload) {
        String id = UUID.randomUUID().toString();
        BigDecimal price = new BigDecimal(payload.getPrice());
        return Mono.fromFuture(commandGateway.send(new HousePurchaseCommand(id,
                price, payload.getType())))
                .cast(String.class);
    }

    @PutMapping(path = "/sell/{id}")
    Mono<String> registerSale(@PathVariable("id") String id,
                               @RequestBody HousePurchasePayload payload) {
        BigDecimal price = new BigDecimal(payload.getPrice());
        return Mono.fromFuture(commandGateway.send(new HouseSellCommand(id,
                price)))
                .cast(String.class)
                .switchIfEmpty(Mono.just(""));
    }

}
