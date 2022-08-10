package com.neeejm.inventory.repositories;

import com.neeejm.inventory.models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PrivilegeRepo extends JpaRepository<Privilege, UUID> {
}
