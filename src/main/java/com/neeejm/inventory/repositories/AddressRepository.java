package com.neeejm.inventory.repositories;

import com.neeejm.inventory.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
    Set<Address> findByCountry(String country);
    Set<Address> findByCity(String city);
}
