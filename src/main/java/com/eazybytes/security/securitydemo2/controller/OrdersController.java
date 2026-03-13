package com.eazybytes.security.securitydemo2.controller;

import com.eazybytes.security.securitydemo2.dto.OrderDto;
import com.eazybytes.security.securitydemo2.exception.CustomerAlreadyExistsException;
import com.eazybytes.security.securitydemo2.exception.ErrorResponse;
import com.eazybytes.security.securitydemo2.repository.OrdersRepository;
import com.eazybytes.security.securitydemo2.service.OrdersService;
import com.eazybytes.security.securitydemo2.service.impl.OrdersServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/orders", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class OrdersController {

    private OrdersServiceImpl ordersService;

    @GetMapping(path="/order/{orderId}")
    public ResponseEntity<OrderDto> fetchAccountDetails(@PathVariable
                                                           Integer orderId, @RequestParam String format, @RequestParam Integer page,
                                                        @RequestParam Integer transactionId) {
        OrderDto orderDto = ordersService.findOrderById(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto){
        OrderDto orderDto1= ordersService.createOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDto1);
    }

    @ExceptionHandler(value = CustomerAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

}
