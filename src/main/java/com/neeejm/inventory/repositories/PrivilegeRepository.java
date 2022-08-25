package com.neeejm.inventory.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.neeejm.inventory.models.Privilege;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, UUID> {
    @RestResource(path = "name")
    Optional<Privilege> findByNameIgnoreCase(@Param("name") String name);

    default Optional<Privilege> findByName(String name) {
        return findByNameIgnoreCase(name);
    }
}
