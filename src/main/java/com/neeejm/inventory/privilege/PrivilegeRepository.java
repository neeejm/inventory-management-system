package com.neeejm.inventory.privilege;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(
    path = "privileges",
    collectionResourceRel = "privileges",
    itemResourceRel = "privilege"
)
public interface PrivilegeRepository extends JpaRepository<PrivilegeEntity, UUID> {
    @RestResource(exported = false)
    Optional<PrivilegeEntity> findByName(String name);
}
