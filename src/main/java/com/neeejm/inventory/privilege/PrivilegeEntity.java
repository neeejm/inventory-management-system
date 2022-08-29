package com.neeejm.inventory.privilege;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.neeejm.inventory.common.util.validators.annotations.ValidEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "privilege")
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PrivilegeEntity implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @ValidEnum(enumClass = PrivilegeName.class)
    @Column(nullable = false, unique = true)
    private String name;

    public enum PrivilegeName {
        OP_ALL,

        OP_CREATE_SALES,
        OP_READ_SALES,
        OP_UPDATE_SALES,
        OP_DELETE_SALES,

        OP_CREATE_PURCHASES,
        OP_READ_PURCHASES,
        OP_UPDATE_PURCHASES,
        OP_DELETE_PURCHASES,

        OP_CREATE_PRODUCTS,
        OP_READ_PRODUCTS,
        OP_UPDATE_PRODUCTS,
        OP_DELETE_PRODUCTS,
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        PrivilegeEntity privilege = (PrivilegeEntity) o;
        return id != null && Objects.equals(id, privilege.id);
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }
}
