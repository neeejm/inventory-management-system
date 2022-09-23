package com.neeejm.inventory.common.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "address")
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity extends BaseEntity {
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
        if (o == null || this.getClass() != o.getClass()) return false;
        AddressEntity address = (AddressEntity) o;
        return getId() != null && Objects.equals(getId(), address.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
