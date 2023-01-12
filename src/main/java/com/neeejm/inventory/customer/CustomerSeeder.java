package com.neeejm.inventory.customer;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.neeejm.inventory.common.entities.AddressEntity;
import com.neeejm.inventory.customer.entities.CompanyEntity;
import com.neeejm.inventory.customer.entities.PersonEntity;
import com.neeejm.inventory.customer.repositories.CompanyRepository;
import com.neeejm.inventory.customer.repositories.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("customerSeeder")
public class CustomerSeeder {
    
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private PersonRepository personRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        loadCompanies();
        loadPersons();
    }

    private void loadCompanies() {
        if (companyRepository.findAll().isEmpty()) {
            companyRepository.save(
                CompanyEntity.builder()
                        .name("comp 1")
                        .email("comp1@mail.com")
                        .primaryPhone("+212612345678")
                        .addresses(Set.of(
                            AddressEntity.builder()
                                    .country("country 3")
                                    .city("city 3")
                                    .street("street 3")
                                    .zipCode("30000")
                                    .build()
                        ))  
                        .build()
            );
            companyRepository.save(
                CompanyEntity.builder()
                        .name("comp 2")
                        .email("comp2@mail.com")
                        .primaryPhone("+212687654321")
                        .addresses(Set.of(
                            AddressEntity.builder()
                                    .country("country 4")
                                    .city("city 4")
                                    .street("street 4")
                                    .zipCode("40000")
                                    .build()
                        ))  
                        .build()
            );
            log.info("[SEED] Companies seeding done");
        } else {
            log.info("[SEED] Companies seeding not required");
        }
    }

    private void loadPersons() {
        if (personRepository.findAll().isEmpty()) {
            personRepository.save(
                PersonEntity.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .email("johndoe@mail.com")
                        .primaryPhone("+212687654321")
                        .addresses(Set.of(
                            AddressEntity.builder()
                                    .country("country 3")
                                    .city("city 3")
                                    .street("street 3")
                                    .zipCode("30000")
                                    .build()
                        ))  
                        .build()
            );
            personRepository.save(
                PersonEntity.builder()
                        .firstName("Jane")
                        .lastName("Doe")
                        .email("janedoe@mail.com")
                        .primaryPhone("+212612345678")
                        .addresses(Set.of(
                            AddressEntity.builder()
                                    .country("country 4")
                                    .city("city 4")
                                    .street("street 4")
                                    .zipCode("40000")
                                    .build()
                        ))  
                        .build()
            );
            log.info("[SEED] Persons seeding done");
        } else {
            log.info("[SEED] Persons seeding not required");
        }
    }
}
