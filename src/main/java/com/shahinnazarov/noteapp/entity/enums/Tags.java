package com.shahinnazarov.noteapp.entity.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Tags {
    UNKNOWN(false), // this tag will be used in case the tag is not recognized
    BUSINESS,
    PERSONAL,
    IMPORTANT;

    private final boolean available;

    Tags() {
        this.available = true; // available by default
    }
}
