package com.neeejm.inventory.privilege.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.neeejm.inventory.category.CategoryEntity;

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
