package com.shahinnazarov.noteapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shahinnazarov.noteapp.entity.enums.Tags;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NoteQueryDto {
    private String id;
    private String title;
    private String content;
    private Set<Tags> tags;
    private Map<String, Integer> wordStats;
    private Instant createdAt;

}
