package com.neeejm.inventory.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String reference;

    @Column(nullable = false)
    private BigDecimal costPrice;

    @Column(nullable = false)
    private BigDecimal unitPrice;

    @Column(columnDefinition = "text")
    private String description;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category subCategory;
}
