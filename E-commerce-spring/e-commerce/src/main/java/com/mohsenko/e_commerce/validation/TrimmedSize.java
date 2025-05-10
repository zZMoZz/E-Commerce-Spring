package com.mohsenko.e_commerce.validation;

import com.mohsenko.e_commerce.constant.AppConstants;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE_USE;

@Documented
@Constraint(validatedBy = TrimmedSizeValidator.class) // specify the validator class which handle validation logic
@Target({ ElementType.FIELD}) // specify the types of elements the annotation can be applied to
@Retention(RetentionPolicy.RUNTIME)
public @interface TrimmedSize {
    String message() default (AppConstants.TRIMMEDSIZE_MESSAGE);
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int min() default 0;
    int max() default Integer.MAX_VALUE;
    String fieldName() default "Field";
}


