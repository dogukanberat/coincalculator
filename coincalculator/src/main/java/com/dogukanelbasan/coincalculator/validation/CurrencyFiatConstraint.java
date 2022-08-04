package com.dogukanelbasan.coincalculator.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CurrencyFiatValidator.class)
@Documented
public @interface CurrencyFiatConstraint {

    String message() default "Not accepted.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

