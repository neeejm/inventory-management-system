package com.neeejm.inventory.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Address {
    @Id
    @GeneratedValue
    private UUID id;

    @NonNull
    @Column(nullable = false)
    private String country;

    @NonNull
    @Column(nullable = false)
    private String city;

    @NonNull
    @Column(nullable = false)
    private String street;

    @NonNull
    @Column(nullable = false)
    private String zipCode;
}
