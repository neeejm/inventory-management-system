package com.neeejm.inventory.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "Can't be blank")
    @Column(nullable = false)
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

    // @NotEmpty(message = "Can't be empty")
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
