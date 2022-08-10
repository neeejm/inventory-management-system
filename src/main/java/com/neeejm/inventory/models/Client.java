package com.neeejm.inventory.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public abstract class Client extends BaseEntity {
    protected String displayName;

    @Column(nullable = false)
    protected String email;

    @Column(nullable = false)
    protected String primaryPhone;

    protected String secondaryPhone;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "address_id")
    @Builder.Default
    private Set<Address> addresses = new HashSet<>();
}
