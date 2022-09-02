package com.neeejm.inventory.role;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    path = "roles",
    collectionResourceRel = "roles",
    itemResourceRel = "role"
)
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
}
