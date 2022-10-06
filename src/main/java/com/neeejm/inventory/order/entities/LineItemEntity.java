package com.neeejm.inventory.order.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.neeejm.inventory.common.entities.BaseEntity;
import com.neeejm.inventory.product.ProductEntity;
import com.neeejm.inventory.stock.entities.StockEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "line_item")
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class LineItemEntity extends BaseEntity {

	@NotNull(message = "Can't be null")
	@Min(value = 1, message = "Can't be less than 1")
	@Column(nullable = false)
	private Integer quantity;

	@NotNull(message = "Can't be null")
	@ManyToOne(
			fetch = FetchType.EAGER,
			optional = false
	)
	@JoinColumn(name = "stock_id")
	private StockEntity stock;

	@NotNull(message = "Can't be null")
	@ManyToOne(
			fetch = FetchType.EAGER,
			optional = false
	)
	@JoinColumn(name = "product_id")
	private ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        LineItemEntity lineItem = (LineItemEntity) o;
        return getId() != null && Objects.equals(getId(), lineItem.getId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (id == null ? 0 : id.hashCode());
        hash = 31 * hash + (quantity == null ? 0 : quantity.hashCode());
        return hash;
    }
}
