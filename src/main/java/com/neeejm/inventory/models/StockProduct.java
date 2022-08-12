package com.neeejm.inventory.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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

    @NotNull(message = "Can't be null")
    @Min(value = 1, message = "Can't be less than 1")
    @Column(nullable = false)
    private Integer quantity;
}
