package com.neeejm.inventory.customvalidator.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber.CountryCodeSource;
import com.neeejm.inventory.customvalidator.annotations.ValidPhoneNumber;

import lombok.Setter;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {
    private final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
    @Setter
    private boolean nullable;

    @Override
    public void initialize(ValidPhoneNumber constraintAnnotation) {
        nullable = constraintAnnotation.nullable();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            return isValidNumber(value);
        } catch (NumberParseException e) {
            return false;
        }
    }

    private boolean isValidNumber(String value) throws NumberParseException {
        if (nullable && value.equals(null)) {
            return true;
        }
        return phoneNumberUtil.isValidNumber(
                phoneNumberUtil.parse(
                        value,
                        CountryCodeSource.UNSPECIFIED.name()
                )
        );
    }
}
