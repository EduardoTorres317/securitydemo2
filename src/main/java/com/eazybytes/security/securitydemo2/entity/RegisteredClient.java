package com.eazybytes.security.securitydemo2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name="clients")
@Getter
@Setter
public class RegisteredClient {
    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clientId;

    @Column(length = 30)
    private String name;

    private java.sql.Date birth_date;

    // Getters and setters
}