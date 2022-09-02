package com.neeejm.inventory.customer.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neeejm.inventory.customer.entities.CompanyEntity;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
}
