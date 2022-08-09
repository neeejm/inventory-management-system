package com.neeejm.inventory.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class Person extends Client {
    @NonNull
    @Column(nullable = false)
    private String firstName;

    @NonNull
    @Column(nullable = false)
    private String lastName;
}
