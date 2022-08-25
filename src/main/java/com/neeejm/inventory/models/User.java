package com.neeejm.inventory.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity{
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
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST}
    )
    @JoinTable(
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
