package com.shahinnazarov.noteapp.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.InputStream;

@UtilityClass
public class JsonUtils {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @SneakyThrows
    public static String asString(final Object object) {
        return OBJECT_MAPPER.writeValueAsString(object);
    }

    @SneakyThrows
    public static <T> T asObject(final InputStream inputStream) {
        return OBJECT_MAPPER.readValue(inputStream, new TypeReference<>() {
        });
    }

    @SneakyThrows
    public static <T> T asObject(final InputStream inputStream, final Class<T> clazz) {
        return OBJECT_MAPPER.readValue(inputStream, clazz);
    }

}
