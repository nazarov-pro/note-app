package com.shahinnazarov.noteapp.utils.converters;

import com.shahinnazarov.noteapp.dto.NoteQueryDto;
import com.shahinnazarov.noteapp.entity.Note;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NoteToNoteQueryDtoConverter implements Converter<Note, NoteQueryDto> {
    @Override
    public NoteQueryDto convert(Note source) {
        return NoteQueryDto.builder()
                .id(source.getId())
                .title(source.getTitle())
                .content(source.getContent())
                .tags(source.getTags())
                .wordStats(source.getWordStats())
                .createdAt(source.getCreatedAt())
                .build();
    }
}
