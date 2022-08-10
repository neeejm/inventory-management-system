package com.neeejm.inventory.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
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
