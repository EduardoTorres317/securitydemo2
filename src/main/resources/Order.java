package com.eazybytes.security.securitydemo2.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order {

    private Integer orderId;

    private List<ItemDto> itemDtos;

}
