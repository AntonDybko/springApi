package com.ug.edu.pl.ap.lab9.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ClosingTimeAfterOpeningTimeValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ClosingTimeAfterOpeningTime {

    String message() default "Closing time must be after opening time";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
