package com.eazybytes.security.securitydemo2.controller;

import com.eazybytes.security.securitydemo2.dto.InstallmentDto;
import com.eazybytes.security.securitydemo2.dto.MonthlyInstallmentDto;
import com.eazybytes.security.securitydemo2.dto.OrderDto;
import com.eazybytes.security.securitydemo2.dto.OutstandingAmountDto;
import com.eazybytes.security.securitydemo2.service.LoansService;
import com.eazybytes.security.securitydemo2.service.helpers.LoanCalculationsHelper;
import com.eazybytes.security.securitydemo2.service.impl.LoansServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class LoansController {

    private LoansServiceImpl loansService;

    public LoansController(LoansServiceImpl loansService) {
        this.loansService = loansService;
    }

    @GetMapping(path="/loans/installment/{clientId}/{loanId}")
    public ResponseEntity<MonthlyInstallmentDto> getInstallmentDetails(@PathVariable
                                                        Long clientId, @PathVariable Long loanId) {
        MonthlyInstallmentDto installment = loansService.getMonthlyLoanInstallment(loanId, clientId);
        return new ResponseEntity<>(installment, HttpStatus.OK);
    }

    @GetMapping(path="/loans/outstanding/{clientId}/{loanId}")
    public ResponseEntity<OutstandingAmountDto> fetchOutstandingAmount(
            @PathVariable Long clientId,
            @PathVariable Long loanId,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate asOf) {

        OutstandingAmountDto outstanding = loansService.getOutstandingLoanAmount(loanId, clientId, asOf);

        return new ResponseEntity<>(outstanding, HttpStatus.OK);
    }

}
