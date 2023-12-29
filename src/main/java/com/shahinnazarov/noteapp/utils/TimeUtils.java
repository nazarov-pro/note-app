package com.shahinnazarov.noteapp.utils;

import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;

@Component
public class TimeUtils {

    public Instant now() {
        return Instant.now(Clock.systemUTC());
    }
}
