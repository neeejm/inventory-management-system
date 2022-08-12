package com.neeejm.inventory.customvalidator;

import com.neeejm.inventory.models.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.Payload;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
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
                return Order.Type.class;
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
                .hasSize(2)
                .containsExactlyInAnyOrder("PURCHASE", "SALE");
    }
    @Test
    void validEnumValue() {
        // Given
        String enumValue = "SALE";

        // When
        boolean expected = enumValidator.isValid(enumValue, null);

        // Then
        assertThat(expected).isTrue();
    }
}