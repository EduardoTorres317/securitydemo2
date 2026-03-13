package com.eazybytes.security.securitydemo2.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemDto {

    private Integer itemId;
    private String itemName;
    private Integer quantity;
    private BigDecimal price;

}
