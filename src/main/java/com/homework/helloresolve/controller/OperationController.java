package com.homework.helloresolve.controller;

import com.homework.helloresolve.LoanService;
import com.homework.helloresolve.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1/operation")
public class OperationController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/loans")
    public Set<Loan> getLoans() {
        return loanService.getLoans();
    }

    @PostMapping("/loans")
    public Loan addLoan(@RequestParam(value = "name") String name,
                        @RequestParam(value = "id") String id,
                        @RequestParam(value = "amount") String amount,
                        @RequestParam(value = "annualInterestRate") String annualInterestRateRaw,
                        @RequestParam(value = "termsInMonths") int termsInMonths) {
        return loanService.addLoan(name.trim(), id.trim(), amount.trim(), annualInterestRateRaw.trim(), termsInMonths);
    }
}
