package com.neeejm.inventory.customer;

import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import com.neeejm.inventory.customer.entities.CompanyEntity;
import com.neeejm.inventory.customer.entities.PersonEntity;
import com.neeejm.inventory.customer.repositories.CompanyRepository;
import com.neeejm.inventory.customer.repositories.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RepositoryEventHandler
public class CustomerListener {

    private final String EXISTS_MSG = "%s '%s' already exists.";

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private PersonRepository personRepository;
    
    @HandleBeforeCreate
    @HandleBeforeSave
    public void throwIfExists(PersonEntity personToCreate) {
        log.info("[PERSON_CHECK]");

        Optional<PersonEntity> person = personRepository.findByEmail(personToCreate.getEmail());
        if (person.isPresent()) {
            log.error("[ROLE_EXISTS] Person: {}", personToCreate.getEmail());
            throw new EntityExistsException(EXISTS_MSG.formatted("Person", personToCreate.getEmail()));
        }
    }

    @HandleBeforeCreate
    @HandleBeforeSave
    public void throwIfExists(CompanyEntity companyToCreate) {
        log.info("[COMPANY_CHECK]");

        Optional<CompanyEntity> company = companyRepository.findByEmail(companyToCreate.getEmail());
        if (company.isPresent()) {
            log.error("[COMPANY_EXISTS] Company: {}", companyToCreate.getEmail());
            throw new EntityExistsException(EXISTS_MSG.formatted("Company", companyToCreate.getEmail()));
        }
    }
}
