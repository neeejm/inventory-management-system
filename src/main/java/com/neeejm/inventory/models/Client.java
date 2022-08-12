package com.neeejm.inventory.models;

import com.neeejm.inventory.customvalidator.ValidPhoneNumber;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
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

    @ValidPhoneNumber
    protected String secondaryPhone;


    @NotEmpty(message = "Can't be empty")
    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "address_id")
    @Builder.Default
    private Set<Address> addresses = new HashSet<>();
}
