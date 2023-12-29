package com.shahinnazarov.noteapp.dto;

import com.shahinnazarov.noteapp.utils.validators.NoteTagConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class NoteUpdateDto {
    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotBlank(message = "Content is mandatory")
    private String content;
    @NoteTagConstraint
    private Set<String> tags;

}
