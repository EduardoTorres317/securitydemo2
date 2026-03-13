package com.eazybytes.security.securitydemo2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @Column(name= "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(length = 30)
    private String name;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    // Getters and setters
}
