package com.breytenbach.escqrs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FetchHouseSummaryQuery {
    int limit;
    int offset;
    HouseSummaryFilter filter;
}
