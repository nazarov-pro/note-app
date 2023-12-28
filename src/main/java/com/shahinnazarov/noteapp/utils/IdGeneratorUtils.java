package com.shahinnazarov.noteapp.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdGeneratorUtils {
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
