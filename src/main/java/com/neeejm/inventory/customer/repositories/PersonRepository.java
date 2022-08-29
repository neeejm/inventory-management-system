package com.neeejm.inventory.customer.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neeejm.inventory.customer.entities.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, UUID> {
}
