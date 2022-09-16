package com.neeejm.inventory.stock;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.Hibernate;

import com.neeejm.inventory.common.entities.AddressEntity;
import com.neeejm.inventory.common.entities.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "stock")
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class StockEntity extends BaseEntity{

	@NotBlank(message = "Can't be blank")
    @Column(unique = true)
	private String name;
	
    @NotNull(message = "Can't be null")
    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            optional = false
    )
    private AddressEntity address;

    // @OneToMany
    // @JoinColumn(name = "stock_id")
    // @Builder.Default
    // private Set<StockProduct> products = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StockEntity stock = (StockEntity) o;
        return getId() != null && Objects.equals(getId(), stock.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
