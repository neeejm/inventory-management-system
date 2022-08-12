package com.neeejm.inventory.services;

import com.neeejm.inventory.models.Address;
import com.neeejm.inventory.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends CrudServiceImpl<AddressRepository, Address>
        implements AddressService {

    @Autowired
    public AddressServiceImpl(AddressRepository repository) {
        super(repository);
    }
}
