package com.shahinnazarov.noteapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shahinnazarov.noteapp.entity.enums.Tags;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Jacksonized
public class NoteQueryDto {
    private String id;
    private String title;
    private String content;
    private Set<Tags> tags;
    @JsonProperty("word_stats")
    private Map<String, Integer> wordStats;
    @JsonProperty("created_at")
    private Instant createdAt;

}
