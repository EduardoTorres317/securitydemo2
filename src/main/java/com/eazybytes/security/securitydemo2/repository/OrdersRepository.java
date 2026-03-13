package com.eazybytes.security.securitydemo2.repository;

import com.eazybytes.security.securitydemo2.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<PurchaseOrder, Integer> {

    public PurchaseOrder findByOrderId(Integer orderId);

}
