package com.eazybytes.security.securitydemo2.service;

import com.eazybytes.security.securitydemo2.dto.MonthlyInstallmentDto;
import com.eazybytes.security.securitydemo2.dto.OutstandingAmountDto;

import java.time.LocalDate;

public interface LoansService {

    public MonthlyInstallmentDto getMonthlyLoanInstallment(Long loanId, Long clientId);

    public OutstandingAmountDto getOutstandingLoanAmount(Long loanId, Long clientId, LocalDate asOf);

}
