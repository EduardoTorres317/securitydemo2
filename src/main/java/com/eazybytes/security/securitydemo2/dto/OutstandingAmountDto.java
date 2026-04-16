package com.eazybytes.security.securitydemo2.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OutstandingAmountDto(
        Long clientId,
        Long loanId,
        BigDecimal outstanding,
        Integer paymentsMade,
        Integer paymentsRemaining,
        LocalDate asOf
) {
}
