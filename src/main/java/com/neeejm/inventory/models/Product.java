package com.neeejm.inventory.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.URL;

import com.neeejm.inventory.customvalidator.annotations.ValidSubcategory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {
    @NotBlank(message = "Can't be blank")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Can't be blank")
    @Column(nullable = false, unique = true)
    private String reference;

    @NotNull(message = "Can't be null")
    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = false,
            message = "Can't be less or equal to 0.0"
    )
    private BigDecimal costPrice;

    @NotNull(message = "Can't be null")
    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = false,
            message = "Can't be less or equal to 0.0"
    )
    private BigDecimal unitPrice;

    @Column(columnDefinition = "text")
    private String description;

    @URL(
            message = "Not a valid image URL",
            protocol = "https",
            regexp = "(\\.(?:jpg|jpeg|png))$"
    )
    private String imageUrl;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    @ValidSubcategory
    private Category subcategory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return getId() != null && Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
