package com.eazybytes.security.securitydemo2.repository;

import com.eazybytes.security.securitydemo2.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoansRepository extends JpaRepository<Loan, Integer> {

    Loan findByLoanId(Integer loanId);

    List<Loan> findByClient_ClientId(Integer clientId);

}
