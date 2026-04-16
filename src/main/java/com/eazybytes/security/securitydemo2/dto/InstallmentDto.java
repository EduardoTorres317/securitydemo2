package com.eazybytes.security.securitydemo2.dto;

import java.math.BigDecimal;

public record InstallmentDto(Long clientId, Long loanId, BigDecimal installment) {
}
