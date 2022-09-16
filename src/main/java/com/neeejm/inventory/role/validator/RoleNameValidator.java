package com.neeejm.inventory.role.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RoleNameValidator implements ConstraintValidator<ValidRoleName, String> {

    // must follow snake_case pattern and start with 'role' whick can't be used twice
    // word can't be longer than 12 and '_word' repetition can't be longer than 3
    private final Pattern ROLE_NAME_REGEX = Pattern.compile(
                            "^(role)(_(?!(role))[a-z]{2,12}){1,3}$",
                            Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean isValid = false;

        if (ROLE_NAME_REGEX.matcher(value).find()) {
            isValid = true;
        }

        return isValid;
    }
    
}
