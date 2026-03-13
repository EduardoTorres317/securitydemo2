package com.eazybytes.security.securitydemo2.repository;

import com.eazybytes.security.securitydemo2.entity.ItemsForOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsForOrderRepository extends JpaRepository<ItemsForOrder,Integer> {

    public ItemsForOrder findItemsForOrderByItemsForOrderId(Integer itemsForOrderId);

}
