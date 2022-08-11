package com.neeejm.inventory.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class StockProduct {
    @EmbeddedId
    private StockProductId id;

    @ManyToOne
    @MapsId("stockId")
    private Stock stock;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;
}
