package com.neeejm.inventory.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.Hibernate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
// @IdClass(StockProductId.class)
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class StockProduct {
    @EmbeddedId
    @Builder.Default
    private StockProductId id = new StockProductId();
    // @Id  
    // @Column(name = "stock_id")
    // @JsonIgnore
    // private UUID stockId;

    // @Id
    // @Column(name = "product_id")
    // @JsonIgnore
    // private UUID productId;

    @ManyToOne
    @MapsId("stockId")
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @NotNull(message = "Can't be null")
    @Min(value = 1, message = "Can't be less than 1")
    @Column(nullable = false)
    private Integer quantity;

    // @Override
    // public boolean equals(Object o) {
    //     if (this == o) return true;
    //     if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    //     StockProduct that = (StockProduct) o;
    //     return stockId != null && productId != null
    //             && Objects.equals(stockId, that.stockId)
    //             && Objects.equals(productId, that.productId);
    // }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(
    //         stockId.hashCode() * productId.hashCode()
    //     );
    // }
}
