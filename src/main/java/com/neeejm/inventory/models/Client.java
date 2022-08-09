package com.neeejm.inventory.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public abstract class Client {
    @Id
    @GeneratedValue
    protected UUID id;

    protected String displayName;

    @NonNull
    @Column(nullable = false)
    protected String email;

    @NonNull
    @Column(nullable = false)
    protected String primaryPhone;

    protected String secondaryPhone;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "address_id"
    )
    private Set<Address> addresses = new HashSet<>();
}
