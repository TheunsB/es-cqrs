package com.breytenbach.escqrs.houseupdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HousePurchasePayload {
    String id;
    String price;
    String type;
}
