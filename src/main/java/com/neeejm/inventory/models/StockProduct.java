package com.neeejm.inventory.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class StockProduct {
    @EmbeddedId
    private StockProductId id;

    @ManyToOne
    @MapsId("stockId")
    private Stock stock;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    @NonNull
    @Column(nullable = false)
    private Integer quantity;
}
