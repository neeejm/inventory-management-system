package com.neeejm.inventory.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity {
    @NotBlank(message = "Can't be blank")
    @Column(nullable = false)
    private String country;

    @NotBlank(message = "Can't be blank")
    @Column(nullable = false)
    private String city;

    @NotBlank(message = "Can't be blank")
    @Column(nullable = false)
    private String street;

    @NotBlank(message = "Can't be blank")
    @Pattern(regexp = "^\\d{5}(?:[- ]?\\d{4})?$")
    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    @ColumnDefault("true")
    @Builder.Default
    private Boolean isPrimary = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Address address = (Address) o;
        return getId() != null && Objects.equals(getId(), address.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
