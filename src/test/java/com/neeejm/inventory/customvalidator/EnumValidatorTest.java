package com.neeejm.inventory.customvalidator;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.annotation.Annotation;

import javax.validation.Payload;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.neeejm.inventory.models.Role;

class EnumValidatorTest {
    private static final EnumValidator enumValidator = new EnumValidator();
    private static ValidEnum validEnum;

    @BeforeAll
    static void setEnumValidator() {
        validEnum = new ValidEnum() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }

            @Override
            public Class<? extends Enum<?>> enumClass() {
                return Role.RoleName.class;
            }

            @Override
            public String message() {
                return "Value not valid";
            }

            @Override
            public Class<?>[] groups() {
                return new Class[0];
            }

            @Override
            public Class<? extends Payload>[] payload() {
                return new Class[0];
            }
        };
        enumValidator.initialize(validEnum);
    }

    @Test
    void checkValidEnumValidator() {
        // Then
        assertThat(enumValidator.getValues())
                .hasSize(3)
                // .containsExactlyInAnyOrder("PURCHASE", "SALE");
                .contains("ROLE_SALES_MANAGER", "ROLE_ADMIN");
    }
    @Test
    void validEnumValue() {
        // Given
        String enumValue = "ROLE_ADMIN";

        // When
        boolean expected = enumValidator.isValid(enumValue, null);

        // Then
        assertThat(expected).isTrue();
    }
}