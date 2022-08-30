package com.neeejm.inventory.role;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RoleNameValidator implements ConstraintValidator<ValidRoleName, String> {

    // must follow snake_case pattern and start with 'ROLE'
    // 'ROLE' cant be used twice
    private final String ROLE_NAME_REGEX = "^(ROLE)(_(?!(ROLE))[a-zA-Z]+)+$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.toUpperCase().matches(ROLE_NAME_REGEX)) {
            return true;
        }
        return false;
    }
    
}
