package com.neeejm.inventory.customer.entities;

import java.util.Objects;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.Hibernate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("company")
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEntity extends CustomerEntity{
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        CompanyEntity company = (CompanyEntity) o;
        return getId() != null && Objects.equals(getId(), company.getId());
    }
}
