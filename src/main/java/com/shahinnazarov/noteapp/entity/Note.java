package com.shahinnazarov.noteapp.entity;

import com.shahinnazarov.noteapp.entity.enums.Tags;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Set;

@Document
@Builder
@Data
public class Note {
    @Id
    private String id;
    private String title;
    private String content;
    private Map<String, Integer> wordStats;
    @Indexed
    private Set<Tags> tags;
    private Instant createdAt;


}
