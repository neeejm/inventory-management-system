package com.neeejm.inventory.models;

import com.neeejm.inventory.customvalidator.ValidPhoneNumber;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    @Column(nullable = false)
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
    @JoinColumn(name = "address_id")
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
