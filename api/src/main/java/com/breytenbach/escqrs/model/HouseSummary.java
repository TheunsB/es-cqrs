package com.breytenbach.escqrs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.math.BigDecimal;

@Entity
@NamedQueries(
    @NamedQuery(
        name = "HouseSummary.fetch",
        query = "SELECT h FROM HouseSummary h WHERE h.id = :id"
    )
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseSummary {
    @Id
    String id;
    BigDecimal price;
    String type;
    BigDecimal sellPrice;
}
