package com.neeejm.inventory.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neeejm.inventory.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
}
