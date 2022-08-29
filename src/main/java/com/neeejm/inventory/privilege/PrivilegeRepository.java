package com.neeejm.inventory.privilege;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<PrivilegeEntity, UUID> {
    @RestResource(path = "name")
    Optional<PrivilegeEntity> findByNameIgnoreCase(@Param("value") String name);

    default Optional<PrivilegeEntity> findByName(String name) {
        return findByNameIgnoreCase(name);
    }
}
