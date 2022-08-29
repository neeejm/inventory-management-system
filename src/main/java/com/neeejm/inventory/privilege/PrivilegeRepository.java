package com.neeejm.inventory.privilege;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    path = "privileges",
    collectionResourceRel = "privileges",
    itemResourceRel = "privilege"
)
public interface PrivilegeRepository extends JpaRepository<PrivilegeEntity, UUID> {
}
