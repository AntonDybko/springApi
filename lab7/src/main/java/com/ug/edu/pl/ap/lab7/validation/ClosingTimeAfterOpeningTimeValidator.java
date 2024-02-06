package com.ug.edu.pl.ap.lab7.validation;

import java.util.Objects;
import com.ug.edu.pl.ap.lab7.domain.Shop;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ClosingTimeAfterOpeningTimeValidator implements ConstraintValidator<ClosingTimeAfterOpeningTime, Shop> {

    @Override
    public void initialize(ClosingTimeAfterOpeningTime constraintAnnotation) {
    }

    @Override
    public boolean isValid(Shop shop, ConstraintValidatorContext context) {
        System.out.println("hello validation");
        if (Objects.isNull(shop) || shop.getClosingTime() == null || shop.getOpeningTime() == null) {
            return true;
        }

        System.out.println(shop.getClosingTime().isAfter(shop.getOpeningTime()));
        //return shop.getClosingTime().isAfter(shop.getOpeningTime());
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
