package com.neeejm.inventory.repositories;

import com.neeejm.inventory.models.Address;
import com.neeejm.inventory.models.Company;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ClientRepositoryTest {
    @Autowired
    private ClientRepository<Company> underTest;

    @Test
    void isClientPersisted() {
        // given
        Address addressOne = Address.builder()
                .country("Country Name")
                .city("City Name")
                .street("Street Name")
                .zipCode("zip-code")
                .build();
        Address addressTwo = Address.builder()
                .country("Country Name 2")
                .city("City Name 2")
                .street("Street Name 2")
                .zipCode("zip-code 2")
                .build();
        Company company = Company.builder()
                .name("Test Company")
                .displayName("Displayed Company Name")
                .email("test@inventory.com")
                .primaryPhone("+2126-12345678")
                .addresses(
                        new HashSet<>(Arrays.asList(
                                addressOne,
                                addressTwo
                        ))
                ).build();
        underTest.save(company);

        // when
        boolean isCompanyCreated = underTest.existsById(company.getId());

        // then
        assertThat(isCompanyCreated).isTrue();
    }
}