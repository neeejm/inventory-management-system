package com.neeejm.inventory.repositories;

import com.neeejm.inventory.models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, UUID> {
    @RestResource(path = "by")
    Optional<Privilege> findByName(@Param("name") String name);
}
