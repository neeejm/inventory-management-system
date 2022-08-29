package com.neeejm.inventory.role;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    @RestResource(path = "name")
    Optional<RoleEntity> findByNameIgnoreCase(@Param("value") String name);

    default Optional<RoleEntity> findByName(String name) {
        return findByNameIgnoreCase(name);
    }
}
