package com.neeejm.inventory.common.util.validators.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.neeejm.inventory.common.util.validators.EnumValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EnumValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEnum {
    Class<? extends Enum<?>> enumClass();
    String message() default "Value not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
