package com.bloomberg.warehouse.annotations;

import com.bloomberg.warehouse.exceptions.CurrencyCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CurrencyCodeValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCurrency {
    String message() default "Invalid ISO currency code";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

