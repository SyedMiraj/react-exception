package com.adventure.react_exception.error.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MarksValidator implements ConstraintValidator<ValidMarks, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // You can pull totalMarks from context if needed, or inject service
        return value != null && value >= 0; // extend logic as needed
    }
}
