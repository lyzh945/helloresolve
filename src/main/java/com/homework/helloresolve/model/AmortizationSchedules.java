package com.homework.helloresolve.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class AmortizationSchedules {
    @NonNull
    private final String principal;
    @NonNull
    private final String interest;
    @NonNull
    private final String balance;
}
