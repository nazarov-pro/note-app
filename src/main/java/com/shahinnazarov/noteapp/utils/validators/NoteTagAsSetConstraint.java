package com.shahinnazarov.noteapp.utils.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NoteTagAsSetValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoteTagAsSetConstraint {
    String message() default "Invalid tag provided";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
