package com.eazybytes.security.securitydemo2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ItemsForOrder {
    @Id
    @Column(name="items_for_order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemsForOrderId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer amount;


}
