package com.breytenbach.escqrs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HousePurchaseEvent {
    String id;
    BigDecimal price;
    String type;
}
