package com.neeejm.inventory.repositories;

import com.neeejm.inventory.models.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AddressRepositoryTest {
    @Autowired
    private AddressRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void checkIfAddressCreated() {
        // given
        Address address = Address.builder()
                .country("Country Name")
                .city("City Name")
                .street("Street Name")
                .zipCode("zip-code")
                .build();
        underTest.save(address);

        // when
        boolean isAddressCreated = underTest.existsById(address.getId());

        // then
        assertThat(isAddressCreated).isTrue();
    }
}