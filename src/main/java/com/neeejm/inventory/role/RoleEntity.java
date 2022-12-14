package com.neeejm.inventory.role;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.neeejm.inventory.common.entities.BaseEntity;
import com.neeejm.inventory.privilege.PrivilegeEntity;
import com.neeejm.inventory.role.validator.ValidRoleName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "role")
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity extends BaseEntity {

    @ValidRoleName
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            joinColumns = { @JoinColumn(name = "role_id") },
            inverseJoinColumns = { @JoinColumn(name = "privilege_id") }
    )
    @ToString.Exclude
    @Builder.Default
    private Set<PrivilegeEntity> privileges = new HashSet<>();

    public enum Role {
        ROLE_SALES_MANAGER,
        ROLE_PURCHASES_MANAGER,
        ROLE_ADMIN
    }

    public void appendPrivilege(PrivilegeEntity privilege) {
        this.privileges.add(privilege);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        RoleEntity role = (RoleEntity) o;
        return id != null && Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (id == null ? 0 : id.hashCode());
        hash = 31 * hash + (name == null ? 0 : name.hashCode());
        return hash;
    }
}
