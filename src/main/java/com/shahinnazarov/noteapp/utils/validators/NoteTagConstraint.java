package com.shahinnazarov.noteapp.utils.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NoteTagValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoteTagConstraint {
    String message() default "Invalid tag provided";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
