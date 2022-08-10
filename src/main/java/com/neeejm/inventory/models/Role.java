package com.neeejm.inventory.models;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private RoleName name;

    public enum  RoleName {
        ROLE_SALES,
        ROLE_PURCHASE,
        ROLE_ADMIN
    }
}
