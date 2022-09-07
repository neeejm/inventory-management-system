package com.neeejm.inventory.role;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(
    path = "roles",
    collectionResourceRel = "roles",
    itemResourceRel = "role"
)
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    @RestResource(exported = false)
    Optional<RoleEntity> findByName(String name);
}
