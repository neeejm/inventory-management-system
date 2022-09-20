package com.neeejm.inventory.customer.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.neeejm.inventory.customer.entities.PersonEntity;

@RepositoryRestResource(
    path = "persons",
    collectionResourceRel = "persons",
    itemResourceRel = "person"
)
public interface PersonRepository extends JpaRepository<PersonEntity, UUID> {

    @RestResource(exported = false)
    Optional<PersonEntity> findByEmail(String email);
}
