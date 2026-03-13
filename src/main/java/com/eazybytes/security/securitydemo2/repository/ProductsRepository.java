package com.eazybytes.security.securitydemo2.repository;

import com.eazybytes.security.securitydemo2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {

    public Product findById(int id);


}
