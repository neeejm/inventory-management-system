package com.neeejm.inventory.common.util.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.neeejm.inventory.category.CategoryEntity;
import com.neeejm.inventory.common.util.validators.annotations.ValidSubcategory;

public class SubcategoryValidator implements ConstraintValidator<ValidSubcategory, CategoryEntity> {

    @Override
    public boolean isValid(CategoryEntity value, ConstraintValidatorContext context) {
        if (value.getType().equalsIgnoreCase(
            CategoryEntity.CategoryType.SUBCATEGORY.toString()
        )) {
            return true;
        }
        return false;
    }
    
}
