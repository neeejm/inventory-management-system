package com.neeejm.inventory.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.neeejm.inventory.entities.Category;
import com.neeejm.inventory.validators.annotations.ValidSubcategory;

public class SubcategoryValidator implements ConstraintValidator<ValidSubcategory, Category> {

    @Override
    public boolean isValid(Category value, ConstraintValidatorContext context) {
        if (value.getType().equalsIgnoreCase(
            Category.CategoryType.SUBCATEGORY.toString()
        )) {
            return true;
        }
        return false;
    }
    
}
