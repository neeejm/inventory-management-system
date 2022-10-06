package com.neeejm.inventory.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(
    path = "users",
    collectionResourceRel = "users",
    itemResourceRel = "user"
)
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @RestResource(exported = false)
    Optional<UserEntity> findByEmail(String email);
}
