package com.neeejm.inventory.entities;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.Hibernate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@IdClass(StockProductId.class)
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class StockProduct {
    // @EmbeddedId
    // private StockProductId id;
    @Id
    @Column(name = "stock_id")
    private UUID stockId;

    @Id
    @Column(name = "product_id")
    private UUID productId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StockProduct that = (StockProduct) o;
        return stockId != null && productId != null
                && Objects.equals(stockId, that.stockId)
                && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            stockId.hashCode() * productId.hashCode()
        );
    }
}
