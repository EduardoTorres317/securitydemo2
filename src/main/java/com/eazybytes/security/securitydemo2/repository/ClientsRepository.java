package com.eazybytes.security.securitydemo2.repository;

import com.eazybytes.security.securitydemo2.entity.RegisteredClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<RegisteredClient, Integer> {

    public RegisteredClient findByClientId(Integer clientId);

}
