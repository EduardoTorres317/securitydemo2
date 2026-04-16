package com.eazybytes.security.securitydemo2.service.impl;

import com.eazybytes.security.securitydemo2.dto.MonthlyInstallmentDto;
import com.eazybytes.security.securitydemo2.dto.OutstandingAmountDto;
import com.eazybytes.security.securitydemo2.entity.Loan;
import com.eazybytes.security.securitydemo2.repository.LoansRepository;
import com.eazybytes.security.securitydemo2.service.LoansService;
import com.eazybytes.security.securitydemo2.service.helpers.LoanCalculationsHelper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LoansServiceImpl implements LoansService {

    public LoansServiceImpl(LoansRepository loansRepository) {
        this.loansRepository = loansRepository;
    }

    LoansRepository loansRepository;

    @Override
    public MonthlyInstallmentDto getMonthlyLoanInstallment(Long loanId, Long clientId) {

        Loan loan = loansRepository.findByLoanId(loanId.intValue());

        BigDecimal installment = LoanCalculationsHelper.getLoanInstallment(loan);

        return new MonthlyInstallmentDto(loanId, clientId, installment);
    }

    @Override
    public OutstandingAmountDto getOutstandingLoanAmount(Long loanId, Long clientId, LocalDate asOf) {

        Loan loan = loansRepository.findByLoanId(loanId.intValue());

        LocalDate effectiveAsOf = (asOf != null) ? asOf : LocalDate.now();
        LocalDate loanDate = loan.getLoanDate().toLocalDate();

        int totalMonths = loan.getMonths() == null ? 0 : loan.getMonths();
        long monthsBetween = ChronoUnit.MONTHS.between(loanDate, effectiveAsOf);
        int paymentsMade = (int) Math.max(0L, Math.min((long) totalMonths, monthsBetween));
        int paymentsRemaining = totalMonths - paymentsMade;

        BigDecimal outstanding = LoanCalculationsHelper.getOutstandingBalance(loan, paymentsMade);

        return new OutstandingAmountDto(clientId, loanId, outstanding, paymentsMade, paymentsRemaining, effectiveAsOf);
    }
}
