package com.shahinnazarov.noteapp.dto;

import lombok.Data;

import java.util.Set;

@Data
public class NoteUpdateDto {
    private String id;
    private String title;
    private String content;
    private Set<String> tags;

}
