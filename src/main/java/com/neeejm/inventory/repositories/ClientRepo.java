package com.neeejm.inventory.repositories;

import com.neeejm.inventory.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepo<T extends Client> extends JpaRepository<T, UUID> {
}
