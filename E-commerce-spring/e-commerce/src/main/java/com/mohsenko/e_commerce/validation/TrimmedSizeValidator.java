package com.mohsenko.e_commerce.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.MessageFormat;


public class TrimmedSizeValidator implements ConstraintValidator<TrimmedSize, String> {

    private int min;
    private int max;
    private String fieldName;

    // retrieve  values from annotation or use default ones
    @Override
    public void initialize(TrimmedSize constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
        this.fieldName = constraintAnnotation.fieldName();
    }


    // validation logic
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // skip validation for null values,this allows @NotNull to handle null validation instead
        if (value == null || value.isBlank()) return true;

        // remove sequence spaces or edge spaces
        String normalized = value.trim().replaceAll(" +", " ");

        // check
        if (normalized.length() < min || normalized.length() > max) {
            // disable default message
            context.disableDefaultConstraintViolation();
            // get default message, modify, return in response
            String message = MessageFormat.format(context.getDefaultConstraintMessageTemplate(), fieldName, min, max);
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return false;
        }

        return true;
    }
}
