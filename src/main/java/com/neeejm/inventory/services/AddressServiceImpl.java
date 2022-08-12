package com.neeejm.inventory.services;

import com.neeejm.inventory.models.Address;
import com.neeejm.inventory.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService{
    private final AddressRepository addressRepository;

    private static final String NOT_FOUND_EXCEPTION_MSG = "Address not found.";

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    @Override
    public Address add(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address update(Address address) {
        if (addressRepository.findById(address.getId()).isEmpty()) {
            throw new EntityNotFoundException(NOT_FOUND_EXCEPTION_MSG);
        }
        return addressRepository.save(address);
    }

    @Override
    public void deleteById(UUID id) {
        if (!addressRepository.existsById(id)) {
            throw new EntityNotFoundException(NOT_FOUND_EXCEPTION_MSG);
        }
        addressRepository.deleteById(id);
    }

    @Override
    public Address findById(UUID id) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isEmpty()) {
            throw new EntityNotFoundException(NOT_FOUND_EXCEPTION_MSG);
        }
        return address.get();
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }
}
