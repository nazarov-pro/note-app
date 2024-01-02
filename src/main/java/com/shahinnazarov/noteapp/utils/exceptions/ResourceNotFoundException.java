package com.shahinnazarov.noteapp.utils.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ResourceNotFoundException extends RuntimeException {
    public static final String FIELD_NAME = "resource_name";

    private final String resourceName;
}
