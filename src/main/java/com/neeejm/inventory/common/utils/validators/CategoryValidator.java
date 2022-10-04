package com.neeejm.inventory.common.utils.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.neeejm.inventory.category.CategoryEntity;
import com.neeejm.inventory.common.utils.validators.annotations.ValidCategory;

public class CategoryValidator implements ConstraintValidator<ValidCategory, CategoryEntity> {

    private CategoryEntity.Type type;
    private boolean nullable;

    @Override
    public void initialize(ValidCategory constraintAnnotation) {
        type = constraintAnnotation.type();
        nullable = constraintAnnotation.nullable();
    }

    @Override
    public boolean isValid(CategoryEntity value, ConstraintValidatorContext context) {
        if (nullable && value == null) {
            return true;
        }

        boolean isValid = false;

        if (value != null && value.getType().equalsIgnoreCase(type.toString())) {
            isValid = true;
        }

        return isValid;
    }

    
    
}
