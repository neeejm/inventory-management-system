package com.neeejm.inventory.customer.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.neeejm.inventory.customer.entities.CompanyEntity;

@RepositoryRestResource(
    path = "companies",
    collectionResourceRel = "companies",
    itemResourceRel = "company"
)
public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {

    @RestResource(exported = false)
    Optional<CompanyEntity> findByEmail(String email);
}
