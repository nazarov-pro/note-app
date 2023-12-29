package com.shahinnazarov.noteapp.service.impl;

import com.shahinnazarov.noteapp.dto.NoteQueryDto;
import com.shahinnazarov.noteapp.dto.NoteUpdateDto;
import com.shahinnazarov.noteapp.entity.Note;
import com.shahinnazarov.noteapp.entity.enums.Tags;
import com.shahinnazarov.noteapp.repo.NoteRepo;
import com.shahinnazarov.noteapp.service.NoteService;
import com.shahinnazarov.noteapp.utils.IdGeneratorUtils;
import com.shahinnazarov.noteapp.utils.TimeUtils;
import com.shahinnazarov.noteapp.utils.WordCounterUtils;
import com.shahinnazarov.noteapp.utils.converters.NoteToNoteQueryDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final NoteRepo repo;
    private final TimeUtils timeUtils;
    private final IdGeneratorUtils idGeneratorUtils;
    private final NoteToNoteQueryDtoConverter noteQueryDtoConverter;

    @Override
    public NoteQueryDto save(final NoteUpdateDto noteUpdateDto) {
        final Note note = Note.builder()
                .id(idGeneratorUtils.generate())
                .title(noteUpdateDto.getTitle())
                .content(noteUpdateDto.getContent())
                .createdAt(timeUtils.now())
                .wordStats(WordCounterUtils.calculate(noteUpdateDto.getContent()))
                .tags(noteUpdateDto.getTags().stream().map(Tags::valueOf).collect(Collectors.toSet()))
                .build();
        return noteQueryDtoConverter.convert(repo.save(note));
    }

    @Override
    public NoteQueryDto update(final String id, final NoteUpdateDto noteUpdateDto) {
        final Note note = Note.builder()
                .id(id)
                .title(noteUpdateDto.getTitle())
                .content(noteUpdateDto.getContent())
                .createdAt(timeUtils.now())
                .wordStats(WordCounterUtils.calculate(noteUpdateDto.getContent()))
                .tags(noteUpdateDto.getTags().stream().map(Tags::valueOf).collect(Collectors.toSet()))
                .build();
        return noteQueryDtoConverter.convert(repo.save(note));
    }

    @Override
    public void delete(String id) {
        repo.deleteById(id);
    }

    @Override
    public Collection<NoteQueryDto> list(Pageable pageable) {
        repo.findAll(pageable);
        return null;
    }

    @Override
    public NoteQueryDto get(String id) {
        Optional<Note> byId = repo.findById(id);
        return null;
    }
}
