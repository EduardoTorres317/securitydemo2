package com.eazybytes.security.securitydemo2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name="orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {
    @Id
    @Column(name="order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private RegisteredClient client;

    @OneToMany(mappedBy = "purchaseOrder",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ItemsForOrder> items;

    //@ManyToMany(cascade = { CascadeType.ALL })
    //@JoinTable(
//            name = "items_for_order",
//            joinColumns = { @JoinColumn(name = "order_id") },
//            inverseJoinColumns = { @JoinColumn(name = "product_id") }
//    )
//    Set<Products> productsSet = new HashSet<>();

    private java.sql.Date order_date;

    // Getters and setters
}