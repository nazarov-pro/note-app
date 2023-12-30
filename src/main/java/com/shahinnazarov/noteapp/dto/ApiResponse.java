package com.shahinnazarov.noteapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.domain.Page;

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
    private Long total;
    private Integer pageSize;
    private Integer pageNumber;

    public static <T> ApiResponse<T> success(final T item) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setItem(item);
        response.setMessage(DEFAULT_SUCCESS_MESSAGE);
        return response;
    }

    public static <T> ApiResponse<T> success(final Page<T> items) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setItems(items.getContent());
        response.setMessage(DEFAULT_SUCCESS_MESSAGE);
        response.setTotal(items.getTotalElements());
        response.setPageNumber(items.getNumber());
        response.setPageSize(items.getSize());
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
