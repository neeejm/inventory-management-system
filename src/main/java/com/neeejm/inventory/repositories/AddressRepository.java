package com.neeejm.inventory.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.neeejm.inventory.entities.Address;

@Repository
@RestResource(exported = false)
public interface AddressRepository extends JpaRepository<Address, UUID> {
    List<Address> findByCountry(String country);
    List<Address> findByCity(String city);
}
