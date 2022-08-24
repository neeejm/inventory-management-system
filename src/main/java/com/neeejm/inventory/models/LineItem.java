package com.neeejm.inventory.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class LineItem extends BaseEntity {

	@NotNull(message = "Can't be null")
	@Min(value = 1, message = "Can't be less than 1")
	@Column(nullable = false)
	private Integer quantity;

	@NotNull(message = "Can't be null")
	@ManyToOne(
			fetch = FetchType.EAGER,
			optional = false
	)
	private StockProduct stockProduct;
}
