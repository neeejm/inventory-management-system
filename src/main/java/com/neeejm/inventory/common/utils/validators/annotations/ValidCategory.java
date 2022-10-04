package com.neeejm.inventory.common.utils.validators.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.neeejm.inventory.category.CategoryEntity;
import com.neeejm.inventory.common.utils.validators.CategoryValidator;

@Documented
@Constraint(validatedBy = CategoryValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCategory {
    boolean nullable() default false;
    CategoryEntity.Type type() default CategoryEntity.Type.SUBCATEGORY;
    String message() default "Not the expected category type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
