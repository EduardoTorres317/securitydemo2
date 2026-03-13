package com.eazybytes.security.securitydemo2.service.impl;

import com.eazybytes.security.securitydemo2.dto.ItemDto;
import com.eazybytes.security.securitydemo2.dto.OrderDto;
import com.eazybytes.security.securitydemo2.entity.ItemsForOrder;
import com.eazybytes.security.securitydemo2.entity.PurchaseOrder;
import com.eazybytes.security.securitydemo2.entity.Product;
import com.eazybytes.security.securitydemo2.entity.RegisteredClient;
import com.eazybytes.security.securitydemo2.repository.ClientsRepository;
import com.eazybytes.security.securitydemo2.repository.OrdersRepository;
import com.eazybytes.security.securitydemo2.repository.ProductsRepository;
import com.eazybytes.security.securitydemo2.service.OrdersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImpl implements OrdersService {

    private OrdersRepository ordersRepository;
    private ClientsRepository clientsRepository;
    private ProductsRepository productsRepository;

    public OrdersServiceImpl(OrdersRepository ordersRepository, ClientsRepository clientsRepository, ProductsRepository productsRepository) {
        this.ordersRepository = ordersRepository;
        this.clientsRepository = clientsRepository;
        this.productsRepository = productsRepository;
    }

    @Transactional
    public OrderDto createOrder(OrderDto orderDto){

        List<ItemDto> itemDtos = orderDto.getItemDtos();

        PurchaseOrder purchaseOrder = new PurchaseOrder();

        RegisteredClient  registeredClient = clientsRepository.findByClientId(orderDto.getClientId());

        purchaseOrder.setClient(registeredClient);
        List<ItemsForOrder> itemsForOrder = purchaseOrder.getItems();

        itemDtos.forEach(itemDto -> {
            ItemsForOrder itemsForOrder1 = new ItemsForOrder();

            Product product = productsRepository.findById(itemDto.getItemId()).orElse(null);

            itemsForOrder1.setPurchaseOrder(purchaseOrder);
            itemsForOrder1.setAmount(itemDto.getQuantity());
            itemsForOrder1.setProduct(product);
            itemsForOrder.add(itemsForOrder1);
        });
        this.ordersRepository.save(purchaseOrder);

        return orderDto;
    }

    public OrderDto findOrderById(Integer orderId){

        Optional<PurchaseOrder> ordersOptional = ordersRepository.findById(orderId);

        PurchaseOrder purchaseOrder = ordersOptional.orElse(null);

        //now convert to Order dto
        OrderDto orderDto = new OrderDto();

        orderDto.setOrderId(purchaseOrder.getOrderId());

        List<ItemsForOrder> itemsForOrder = purchaseOrder.getItems();
        List<ItemDto> itemDtos = orderDto.getItemDtos();
        for (ItemsForOrder itemsForOrderItem : itemsForOrder){
            ItemDto itemDto = new ItemDto();
            itemDto.setItemName(itemsForOrderItem.getProduct().getName());
            itemDto.setPrice(itemsForOrderItem.getProduct().getPrice());
            itemDto.setQuantity(itemsForOrderItem.getAmount());
            itemDtos.add(itemDto);
        }

        return orderDto;
    }


}
