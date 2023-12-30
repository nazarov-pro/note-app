package com.shahinnazarov.noteapp.utils.validators;

import com.shahinnazarov.noteapp.entity.enums.Tags;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class NoteTagAsStringValidator implements ConstraintValidator<NoteTagAsStringConstraint, String> {
    private final Set<String> allowedTags;

    public NoteTagAsStringValidator() {
        this.allowedTags = Arrays.stream(Tags.values())
                .filter(Tags::isAvailable)
                .map(Tags::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true;
        }
        return allowedTags.containsAll(Arrays.asList(value.split(",")));
    }
}
