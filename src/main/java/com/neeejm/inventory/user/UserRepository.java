package com.neeejm.inventory.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    path = "users",
    collectionResourceRel = "users",
    itemResourceRel = "user"
)
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
