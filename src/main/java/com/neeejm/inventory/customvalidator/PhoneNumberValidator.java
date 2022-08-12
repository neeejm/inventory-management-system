package com.neeejm.inventory.customvalidator;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber.CountryCodeSource;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {
    private final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            return isValidNumber(value);
        } catch (NumberParseException e) {
            return false;
        }
    }

    private boolean isValidNumber(String value) throws NumberParseException {
        return phoneNumberUtil.isValidNumber(
                phoneNumberUtil.parse(
                        value,
                        CountryCodeSource.UNSPECIFIED.name()
                )
        );
    }
}
