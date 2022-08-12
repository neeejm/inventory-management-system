package com.neeejm.inventory.services;

import com.neeejm.inventory.models.Person;
import com.neeejm.inventory.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl extends CrudServiceImpl<PersonRepository, Person>
        implements PersonService {

    @Autowired
    protected PersonServiceImpl(PersonRepository repository) {
        super(repository);
    }
}
