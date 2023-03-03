package com.breytenbach.escqrs.houseupdate;

import com.breytenbach.escqrs.model.FetchHouseSummaryQuery;
import com.breytenbach.escqrs.model.HousePurchaseEvent;
import com.breytenbach.escqrs.model.HouseSellEvent;
import com.breytenbach.escqrs.model.HouseSummary;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Service
@ProcessingGroup("house-summary")
public class HouseSummaryProjection {

    private final EntityManager entityManager;
    private final QueryUpdateEmitter queryUpdateEmitter;

    public HouseSummaryProjection(EntityManager entityManager,
                                  QueryUpdateEmitter queryUpdateEmitter) {
        this.entityManager = entityManager;
        this.queryUpdateEmitter = queryUpdateEmitter;
    }

    @EventHandler
    public void on(HousePurchaseEvent event) {
        entityManager.persist(HouseSummary.builder()
                .id(event.getId())
                .price(event.getPrice())
                .type(event.getType())
                .build());

    }

    @EventHandler
    public void on(HouseSellEvent event) {
        HouseSummary summary = entityManager.find(HouseSummary.class, event.getId());
        summary.setSellPrice(event.getPrice());

        queryUpdateEmitter.emit(FetchHouseSummaryQuery.class,
                query -> event.getId().contentEquals(query.getFilter().getId()),
                summary);
    }

    @QueryHandler
    public HouseSummary handle(FetchHouseSummaryQuery query) {
        TypedQuery<HouseSummary> jpaQuery = entityManager.createNamedQuery("HouseSummary.fetch", HouseSummary.class);
        jpaQuery.setParameter("id", query.getFilter().getId());
        jpaQuery.setFirstResult(query.getOffset());
        jpaQuery.setMaxResults(query.getLimit());
        return jpaQuery.getSingleResult();
    }

}
