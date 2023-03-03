package com.breytenbach.escqrs.housequery;

import com.breytenbach.escqrs.model.FetchHouseSummaryQuery;
import com.breytenbach.escqrs.model.HouseSummary;
import com.breytenbach.escqrs.model.HouseSummaryFilter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/house")
public class HouseController {
    private final QueryGateway queryGateway;

    public HouseController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }
    @GetMapping(path = "{id}")
    Mono<HouseSummary> findHouse(@PathVariable("id") String id) {
        return Mono.fromFuture(queryGateway.query(FetchHouseSummaryQuery.builder()
                .filter(new HouseSummaryFilter(id))
                .limit(100)
                .offset(0)
                .build(),
                HouseSummary.class));
    }
}
