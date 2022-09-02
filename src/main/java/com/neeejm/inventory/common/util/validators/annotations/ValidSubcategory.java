package com.neeejm.inventory.common.util.validators.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.neeejm.inventory.common.util.validators.SubcategoryValidator;

@Documented
@Constraint(validatedBy = SubcategoryValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSubcategory {
    String message() default "Not a subcategory";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
