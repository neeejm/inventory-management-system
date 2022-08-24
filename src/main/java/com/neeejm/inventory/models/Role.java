package com.neeejm.inventory.models;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.Hibernate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

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

    @NotBlank(message = "Can't be blank")
    @Column(nullable = false)
    private String name;

    @ManyToMany(
            fetch = FetchType.LAZY
    )
    @JoinTable(
            inverseJoinColumns = { @JoinColumn(name = "privilege_id") }
    )
    @ToString.Exclude
    @Builder.Default
    private Set<Privilege> privileges = new HashSet<>();

    public enum RoleName {
        ROLE_SALES_MANAGER,
        ROLE_PURCHASES_MANAGER,
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
