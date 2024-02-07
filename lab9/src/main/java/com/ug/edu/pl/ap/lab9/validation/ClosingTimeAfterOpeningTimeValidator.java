package com.ug.edu.pl.ap.lab9.validation;

import com.ug.edu.pl.ap.lab9.domain.Shop;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class ClosingTimeAfterOpeningTimeValidator implements ConstraintValidator<ClosingTimeAfterOpeningTime, Shop> {

    @Override
    public void initialize(ClosingTimeAfterOpeningTime constraintAnnotation) {
    }

    @Override
    public boolean isValid(Shop shop, ConstraintValidatorContext context) {
        if (Objects.isNull(shop) || shop.getClosingTime() == null || shop.getOpeningTime() == null) {
            return true;
        }

        boolean isValid = shop.getClosingTime().isAfter(shop.getOpeningTime());
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Closing time must be after opening time")
                    .addPropertyNode("closingTime")
                    .addConstraintViolation();
        }
        return isValid;
    }
}
