package com.neeejm.inventory.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {
    @NotBlank(message = "Can't be blank")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Can't be blank")
    @Column(nullable = false)
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

    @NotNull(message = "Can't be null")
    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    private Category subCategory;
}
