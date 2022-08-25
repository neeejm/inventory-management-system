package com.neeejm.inventory.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.Hibernate;

import com.neeejm.inventory.customvalidator.annotations.ValidPhoneNumber;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class Client extends BaseEntity {
    protected String displayName;

    @NotBlank(message = "Can't be blank")
    @Email(message = "Not a valid email.")
    @Column(nullable = false, unique = true)
    protected String email;

    @NotBlank(message = "Can't be blank")
    @ValidPhoneNumber
    @Column(nullable = false)
    protected String primaryPhone;

    @ValidPhoneNumber(nullable = true)
    protected String secondaryPhone;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "client_id")
    @Builder.Default
    private Set<Address> addresses = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Client client = (Client) o;
        return getId() != null && Objects.equals(getId(), client.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
