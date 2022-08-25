package com.neeejm.inventory.customvalidator.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.neeejm.inventory.customvalidator.validators.PhoneNumberValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPhoneNumber {
    String message() default "Invalid phone number";
    boolean nullable() default false;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}