package com.homework.helloresolve.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Loan {


    public static final int DEFAULT_SCALE = 10;
    public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_EVEN;
    private static final DecimalFormat FORMAT = new DecimalFormat("0.00");
    private static final String DOLLAR_SIGN = "$";
    static {
        FORMAT.setRoundingMode(DEFAULT_ROUNDING_MODE);
    }
    @NonNull
    private final String name;
    @NonNull
    private final String id;
    @NonNull
    @JsonIgnore
    private final BigDecimal amount;
    @NonNull
    @JsonIgnore
    private final BigDecimal annualInterestRate;
    @JsonIgnore
    private final int termsInMonths;
    @EqualsAndHashCode.Exclude
    private List<AmortizationSchedules> amortizationSchedules;

    public List<AmortizationSchedules> getAmortizationSchedules() {
        if (amortizationSchedules == null) {
            amortizationSchedules = calculateAmortizationSchedules();
        }
        return amortizationSchedules;
    }

    private List<AmortizationSchedules> calculateAmortizationSchedules() {
        amount.setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
        annualInterestRate.setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
        BigDecimal monthlyInterestRate = annualInterestRate.divide(new BigDecimal(12), DEFAULT_ROUNDING_MODE).setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);

        BigDecimal balance = amount;
        List<AmortizationSchedules> amortizationSchedules = new ArrayList<>();
        for (int i = 0 ; i < termsInMonths ; i++) {

            BigDecimal A = amount.multiply(monthlyInterestRate.add(monthlyInterestRate.divide(monthlyInterestRate.add(BigDecimal.ONE).pow(termsInMonths).subtract(BigDecimal.ONE), DEFAULT_ROUNDING_MODE)));
            BigDecimal interest = balance.multiply(monthlyInterestRate);
            BigDecimal principal = A.subtract(interest);
            balance = balance.subtract(principal);
            amortizationSchedules.add(AmortizationSchedules.builder()
                    .balance(DOLLAR_SIGN + FORMAT.format(balance))
                    .principal(DOLLAR_SIGN + FORMAT.format(principal))
                    .interest(DOLLAR_SIGN + FORMAT.format(interest))
                    .build());
        }
        return amortizationSchedules;
    }
}
