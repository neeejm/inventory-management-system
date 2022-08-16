package com.neeejm.inventory.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StockProduct that = (StockProduct) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
