package com.shahinnazarov.noteapp.utils.validators;

import com.shahinnazarov.noteapp.entity.enums.Tags;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class NoteTagAsSetValidator implements ConstraintValidator<NoteTagAsSetConstraint, Set<String>> {
    private final Set<String> allowedTags;

    public NoteTagAsSetValidator() {
        this.allowedTags = Arrays.stream(Tags.values())
                .filter(Tags::isAvailable)
                .map(Tags::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(Set<String> value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        return allowedTags.containsAll(value);
    }
}
