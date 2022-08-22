package com.neeejm.inventory.models;


import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.Hibernate;

import com.neeejm.inventory.customvalidator.ValidEnum;

import lombok.AllArgsConstructor;
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

    // @NotNull(message = "Can't be null")
    @ValidEnum(enumClass = RoleName.class)
    @Column(nullable = false)
    private String name;

    // @NotEmpty(message = "Can't be empty")
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
