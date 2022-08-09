package com.neeejm.inventory.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private UUID id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(nullable = false)
    private String reference;

    @NonNull
    @Column(nullable = false)
    private BigDecimal costPrice;

    @NonNull
    @Column(nullable = false)
    private BigDecimal unitPrice;

    @Column(columnDefinition = "text")
    private String description;

    private String imageUrl;
}
