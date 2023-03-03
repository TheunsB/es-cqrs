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
public class HouseSellEvent {
    String id;
    BigDecimal price;
    String time;
    String type;
}
