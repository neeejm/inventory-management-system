package com.neeejm.inventory.order.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.neeejm.inventory.common.entities.BaseEntity;
import com.neeejm.inventory.stockproduct.StockProductEntity;

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
	@JoinColumns({
		@JoinColumn(name = "stock_id"),
		@JoinColumn(name = "product_id")
	})
	private StockProductEntity stockProduct;
}
