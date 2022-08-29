package com.neeejm.inventory.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockProduct {
    
    private UUID id;

    private String name;

    // private String reference;

    // private BigDecimal costPrice;

    // private BigDecimal unitPrice;

    private Integer quantity;

    // private String description;

    // private String imageUrl;
}
