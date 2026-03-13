package com.eazybytes.security.securitydemo2.repository;

import com.eazybytes.security.securitydemo2.entity.Product;
import com.eazybytes.security.securitydemo2.entity.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Integer> {

    Optional<RegisteredUser> findByEmail(String email);

}
