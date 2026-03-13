package com.eazybytes.security.securitydemo2.service;

import com.eazybytes.security.securitydemo2.dto.OrderDto;

public interface OrdersService {

    public OrderDto createOrder(OrderDto orderDto);

    public OrderDto findOrderById(Integer orderId);

}
