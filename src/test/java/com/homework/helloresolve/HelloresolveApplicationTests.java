package com.homework.helloresolve;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class HelloresolveApplicationTests {

	@Test
	void contextLoads() {
		LoanService loanService = new LoanService();
		loanService.addLoan("Tom", "123", "$300")
	}

}
