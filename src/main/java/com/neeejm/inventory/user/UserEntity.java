package com.neeejm.inventory.user;

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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.Hibernate;

import com.neeejm.inventory.common.entities.BaseEntity;
import com.neeejm.inventory.role.RoleEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "\"user\"")
@SuperBuilder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity{
    @NotBlank(message = "Can't be blank")
    @Email(message = "Not a valid email")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Can't be blank")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Can't be blank")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Can't be blank")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "Can't be blank")
    @Column(nullable = false)
    private String lastName;

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    @ToString.Exclude
    @Builder.Default
    private Set<RoleEntity> roles = new HashSet<>();

    public void appendRole(RoleEntity role) {
        this.roles.add(role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserEntity user = (UserEntity) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (id == null ? 0 : id.hashCode());
        hash = 31 * hash + (email == null ? 0 : email.hashCode());
        hash = 31 * hash + (password == null ? 0 : password.hashCode());
        return hash;
    }
}
