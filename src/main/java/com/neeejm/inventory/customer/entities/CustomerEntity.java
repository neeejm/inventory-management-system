package com.neeejm.inventory.customer.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.Hibernate;
import org.hibernate.annotations.DiscriminatorFormula;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neeejm.inventory.common.entities.AddressEntity;
import com.neeejm.inventory.common.entities.BaseEntity;
import com.neeejm.inventory.common.util.validators.annotations.ValidPhoneNumber;
import com.neeejm.inventory.customer.CustomerDeserializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "customer")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="customer_type", 
    discriminatorType = DiscriminatorType.STRING
)
@DiscriminatorFormula("case when name is null then 'person' else 'company' end")
@JsonDeserialize(using = CustomerDeserializer.class)
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class CustomerEntity extends BaseEntity {
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
    @JoinColumn(name = "customer_id")
    @Builder.Default
    private Set<AddressEntity> addresses = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CustomerEntity customer = (CustomerEntity) o;
        return getId() != null && Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
