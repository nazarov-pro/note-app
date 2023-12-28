package com.shahinnazarov.noteapp.utils;

import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class TimeUtils {

    public OffsetDateTime now() {
        return OffsetDateTime.now();
    }
}
