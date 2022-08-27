package com.neeejm.inventory.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.neeejm.inventory.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    @RestResource(path = "name")
    Optional<Role> findByNameIgnoreCase(@Param("value") String name);

    default Optional<Role> findByName(String name) {
        return findByNameIgnoreCase(name);
    }
}
