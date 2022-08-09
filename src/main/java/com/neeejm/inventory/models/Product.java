package com.neeejm.inventory.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private String reference;

    @NotNull
    private BigDecimal costPrice;

    @NotNull
    private BigDecimal unitPrice;

    @Column(columnDefinition = "text")
    private String description;

    private String imageUrl;
}
