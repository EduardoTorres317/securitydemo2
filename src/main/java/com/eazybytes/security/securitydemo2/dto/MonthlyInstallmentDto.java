package com.eazybytes.security.securitydemo2.dto;

import java.math.BigDecimal;

public record MonthlyInstallmentDto(Long clientId, Long loanId, BigDecimal monthlyInstallment) {
}
