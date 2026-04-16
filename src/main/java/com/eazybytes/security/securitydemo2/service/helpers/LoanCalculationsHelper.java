package com.eazybytes.security.securitydemo2.service.helpers;

import com.eazybytes.security.securitydemo2.entity.Loan;

import java.math.BigDecimal;

public class LoanCalculationsHelper {

    /**
     * E = principal * rate * ((1 + r)to N months/ ())
     * @param loan
     * @return
     */

    public static BigDecimal getLoanInstallment(Loan loan) {

        double toNMonths = toNMonths(loan.getInterestRate(), loan.getMonths());

        double nMOnthsDenominator = toNMonths - 1;

        return BigDecimal.valueOf(loan.getInitialAmount().doubleValue() * loan.getInterestRate().doubleValue() * (toNMonths/nMOnthsDenominator));
    }

    private static Double toNMonths(BigDecimal rate, Integer monthsTenure){
        double monthlyBase = 1 + rate.doubleValue();
        double result = monthlyBase;
        for (int i = 0; i < monthsTenure; i++){
            result *= monthlyBase;
        }
        return result;
    }

    /**
     * Outstanding principal balance after {@code paymentsMade} installments on a level-payment loan.
     *
     *   balance(N) = P * ((1+r)^n - (1+r)^N) / ((1+r)^n - 1)
     *
     * where P = initial principal, r = monthly rate, n = total months, N = payments already made.
     * For a zero-interest loan the formula degenerates; we fall back to linear amortization:
     *   balance(N) = P * (1 - N/n)
     *
     * @param loan         loan to compute against (uses initialAmount, interestRate, months)
     * @param paymentsMade how many monthly installments have been paid; will be clamped to [0, months]
     * @return remaining principal as a BigDecimal
     */
    public static BigDecimal getOutstandingBalance(Loan loan, int paymentsMade) {
        int totalMonths = loan.getMonths() == null ? 0 : loan.getMonths();
        BigDecimal principal = loan.getInitialAmount();
        BigDecimal rate = loan.getInterestRate();

        if (totalMonths <= 0 || principal == null) {
            return BigDecimal.ZERO;
        }
        if (paymentsMade <= 0) {
            return principal;
        }
        if (paymentsMade >= totalMonths) {
            return BigDecimal.ZERO;
        }

        double r = rate == null ? 0d : rate.doubleValue();

        // Zero-interest loan: linear amortization
        if (r == 0d) {
            double remaining = principal.doubleValue() * (1d - (double) paymentsMade / (double) totalMonths);
            return BigDecimal.valueOf(remaining);
        }

        double powN = Math.pow(1d + r, totalMonths);
        double powK = Math.pow(1d + r, paymentsMade);
        double balance = principal.doubleValue() * (powN - powK) / (powN - 1d);
        return BigDecimal.valueOf(balance);
    }

}
