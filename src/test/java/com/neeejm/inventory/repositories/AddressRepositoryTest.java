package com.neeejm.inventory.repositories;

import com.neeejm.inventory.models.Address;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest(
//        properties = {
//                "spring.jpa.properties.javax.persistence.validation.mode=none"
//        }
)
class AddressRepositoryTest {
    @Autowired
    private AddressRepository underTest;
    private static Validator validator;

    @BeforeAll
    static void setValidator() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory();) {
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("it should not accept blank country names")
    void validateCountryName() {
        // Given
        Address address = Address.builder()
                .country("")
                .city("city")
                .street("street")
                .zipCode("12345")
                .build();

        // When
        Set<ConstraintViolation<Address>> expectedConstraintViolations =
                validator.validate(address);

        assertThat(expectedConstraintViolations).hasSize(1);
        assertThat(expectedConstraintViolations.iterator().next().getMessage())
                .contains("Can't be blank");
    }

    @Test
    @DisplayName("it should not accept blank city names")
    void validateCityName() {
        // Given
        Address address = Address.builder()
                .country("country")
                .city("")
                .street("street")
                .zipCode("12345")
                .build();

        // When
        Set<ConstraintViolation<Address>> expectedConstraintViolations =
                validator.validate(address);

        assertThat(expectedConstraintViolations).hasSize(1);
        assertThat(expectedConstraintViolations.iterator().next().getMessage())
                .contains("Can't be blank");
    }

    @Test
    @DisplayName("it should not accept blank street addresses")
    void validateStreetAddress() {
        // Given
        Address address = Address.builder()
                .country("country")
                .city("city")
                .street("")
                .zipCode("12345")
                .build();

        // When
        Set<ConstraintViolation<Address>> expectedConstraintViolations =
                validator.validate(address);

        assertThat(expectedConstraintViolations).hasSize(1);
        assertThat(expectedConstraintViolations.iterator().next().getMessage())
                .contains("Can't be blank");
    }

}