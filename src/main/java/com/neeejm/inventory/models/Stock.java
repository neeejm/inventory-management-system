package com.neeejm.inventory.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Stock {
    @Id
    @GeneratedValue
    private UUID id;

    @NonNull
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private Address address;
}
