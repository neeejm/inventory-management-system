package com.neeejm.inventory.models;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private RoleName name;

    @ManyToMany
    @JoinTable(
            inverseJoinColumns = { @JoinColumn(name = "privilege_id") }
    )
    private Set<Privilege> privileges;

    public enum  RoleName {
        ROLE_SALES,
        ROLE_PURCHASE,
        ROLE_ADMIN
    }
}
