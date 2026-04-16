package com.eazybytes.security.securitydemo2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "Loans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    @Id
    @Column(name = "loan_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loanId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private RegisteredClient client;

    private java.sql.Date loanDate;

    @Column(precision = 10, scale = 2)
    private BigDecimal initialAmount;

    @Column(precision = 10, scale = 2)
    private BigDecimal outstandingAmount;

    @Column(precision = 10, scale = 2)
    private BigDecimal interestRate;

    private Integer months;

}
