package com.neeejm.inventory.models;


import com.neeejm.inventory.customvalidator.ValidEnum;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank(message = "Can't be blank")
    @ValidEnum(enumClass = RoleName.class)
    @Column(nullable = false)
    private RoleName name;

    @NotEmpty(message = "Can't be empty")
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST}
    )
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
