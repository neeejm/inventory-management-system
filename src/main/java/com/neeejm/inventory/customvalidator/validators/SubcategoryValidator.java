package com.neeejm.inventory.customvalidator.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.neeejm.inventory.customvalidator.annotations.ValidSubcategory;
import com.neeejm.inventory.models.Category;

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
