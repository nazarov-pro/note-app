package com.shahinnazarov.noteapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Collection;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ApiResponse<T> {
    public static final String DEFAULT_SUCCESS_MESSAGE = "ok";
    public static final String DEFAULT_ERROR_MESSAGE = "failed";

    private String message;
    private T errors;
    private T item;
    private Collection<T> items;

    public static <T> ApiResponse<T> success(final T item) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setItem(item);
        response.setMessage(DEFAULT_SUCCESS_MESSAGE);
        return response;
    }

    public static <T> ApiResponse<T> success(final Collection<T> items) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setItems(items);
        response.setMessage(DEFAULT_SUCCESS_MESSAGE);
        return response;
    }

    public static <T> ApiResponse<T> success() {
        ApiResponse<T> response = new ApiResponse<>();
        response.setMessage(DEFAULT_SUCCESS_MESSAGE);
        return response;
    }

    public static <T> ApiResponse<T> errors(final T body) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setMessage(DEFAULT_ERROR_MESSAGE);
        response.setErrors(body);
        return response;
    }
}
