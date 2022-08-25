package com.neeejm.inventory.customvalidator;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import lombok.Getter;

public class EnumValidator implements ConstraintValidator<ValidEnum, String> {
    @Getter
    private Set<String> values;

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        values = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
            .map(Enum::name)
            .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
            return values.contains(value.toUpperCase());
    }
}
