package com.neeejm.inventory.customvalidator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PhoneNumberValidatorTest {
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