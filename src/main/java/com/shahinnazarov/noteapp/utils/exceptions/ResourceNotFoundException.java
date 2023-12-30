package com.shahinnazarov.noteapp.utils.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final String resourceName;
}
