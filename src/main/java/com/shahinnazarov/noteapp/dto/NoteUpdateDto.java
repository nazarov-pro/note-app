package com.shahinnazarov.noteapp.dto;

import com.shahinnazarov.noteapp.utils.validators.NoteTagAsSetConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class NoteUpdateDto {
    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotBlank(message = "Content is mandatory")
    private String content;
    @NoteTagAsSetConstraint
    private Set<String> tags;

}
