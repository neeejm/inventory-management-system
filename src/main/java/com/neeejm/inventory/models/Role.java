package com.neeejm.inventory.models;


import com.neeejm.inventory.customvalidator.ValidEnum;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @NotNull(message = "Can't be null")
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
    @ToString.Exclude
    private Set<Privilege> privileges;

    public enum  RoleName {
        ROLE_SALES,
        ROLE_PURCHASE,
        ROLE_ADMIN
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Role role = (Role) o;
        return id != null && Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
