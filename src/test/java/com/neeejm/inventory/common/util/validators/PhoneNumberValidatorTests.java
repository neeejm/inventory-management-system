package com.neeejm.inventory.common.util.validators;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PhoneNumberValidatorTests {
    private final PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();

    @Test
    @DisplayName("Should be a valid phone number")
    void validPhoneNumber() {
        // Given
        String phoneNumber = "+447716535176";

        // When
        boolean expected = phoneNumberValidator.isValid(phoneNumber, null);

        // Then
        assertThat(expected).isTrue();
    }

    @Test
    void shouldBeAValidPhoneWhenNullableIsTrue() {
        // Given
        phoneNumberValidator.setNullable(true);
        String phoneNumber = null;

        // When
        boolean expected = phoneNumberValidator.isValid(phoneNumber, null);

        // Then
        assertThat(expected).isTrue();
    }

    @Test
    @DisplayName("Should not be a valid phone number")
    void notAValidPhoneNumber() {
        // Given
        String phoneNumber = "972552603210";

        // When
        boolean expected = phoneNumberValidator.isValid(phoneNumber, null);

        // Then
        assertThat(expected).isFalse();
    }
}