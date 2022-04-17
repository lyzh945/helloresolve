package com.homework.helloresolve;

import com.homework.helloresolve.model.Loan;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import static com.homework.helloresolve.model.Loan.DEFAULT_ROUNDING_MODE;
import static com.homework.helloresolve.model.Loan.DEFAULT_SCALE;

@Service
public class LoanService {


    private final Set<Loan> loans = new HashSet<>();

    public Loan addLoan(String name, String id, String amountRaw, String annualInterestRateRaw, int termsInMonths) {
        BigDecimal amount = BigDecimal.valueOf(Long.parseLong(amountRaw.substring(1))).setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
        BigDecimal annualInterestRate = BigDecimal.valueOf(Double.parseDouble(annualInterestRateRaw.substring(0, annualInterestRateRaw.length() - 1)) / 100).setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
        Loan loan = Loan.builder()
                .name(name)
                .id(id)
                .amount(amount)
                .annualInterestRate(annualInterestRate)
                .termsInMonths(termsInMonths)
                .build();
        loans.add(loan);
        return loan;
    }

    public Set<Loan> getLoans() {
        return loans;
    }
}
