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
import com.shahinnazarov.noteapp.utils.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoteServiceImpl implements NoteService {
    private final NoteRepo repo;
    private final TimeUtils timeUtils;
    private final IdGeneratorUtils idGeneratorUtils;
    private final NoteToNoteQueryDtoConverter noteQueryDtoConverter;
    private final MongoTemplate mongoTemplate;

    @Override
    public NoteQueryDto save(final NoteUpdateDto noteUpdateDto) {
        log.info("Saving note: {}", noteUpdateDto);
        final Note note = Note.builder()
                .id(idGeneratorUtils.generate())
                .title(noteUpdateDto.getTitle())
                .content(noteUpdateDto.getContent())
                .createdAt(timeUtils.now())
                .wordStats(WordCounterUtils.calculate(noteUpdateDto.getContent()))
                .build();
        if (noteUpdateDto.getTags() != null) {
            note.setTags(noteUpdateDto.getTags().stream().map(Tags::valueOf).collect(Collectors.toSet()));
        }
        return noteQueryDtoConverter.convert(repo.save(note));
    }

    @Override
    public NoteQueryDto update(final String id, final NoteUpdateDto noteUpdateDto) {
        log.info("Updating note[{}]: {}", id, noteUpdateDto);

        final Note note = Note.builder()
                .id(id)
                .title(noteUpdateDto.getTitle())
                .content(noteUpdateDto.getContent())
                .createdAt(timeUtils.now())
                .wordStats(WordCounterUtils.calculate(noteUpdateDto.getContent()))
                .build();
        if (noteUpdateDto.getTags() != null) {
            note.setTags(noteUpdateDto.getTags().stream().map(Tags::valueOf).collect(Collectors.toSet()));
        }

        return noteQueryDtoConverter.convert(repo.save(note));
    }

    @Override
    public void delete(final String id) {
        log.info("Deleting note: {}", id);
        repo.deleteById(id);
    }

    @Override
    public Page<NoteQueryDto> list(final boolean minimalResponse, final String tags, final PageRequest pageRequest) {
        log.info("List request received | minimal: {} / tags: {} / page request: {}", minimalResponse, tags, pageRequest);
        final Set<Tags> setOfTags = Arrays.stream(tags.split(",")).map(Tags::valueOf)
                .collect(Collectors.toSet());
        final Query query = new Query();
        if (!setOfTags.isEmpty()) {
            final Criteria criteria = where("tags").in(setOfTags);
            query.addCriteria(criteria);
        }

        final long totalElements = mongoTemplate.count(query, Note.class);

        if (minimalResponse) {
            query.fields().include("title", "createdAt");
        }

        query.with(pageRequest.withSort(Sort.by(Sort.Direction.DESC, "createdAt")));

        final List<NoteQueryDto> notes = mongoTemplate.find(query, Note.class).stream()
                .map(noteQueryDtoConverter::convert)
                .toList();

        return new PageImpl<>(notes, pageRequest, totalElements);
    }

    @Override
    public NoteQueryDto get(final String id) {
        log.info("Get note by {}", id);
        return repo.findById(id).map(noteQueryDtoConverter::convert)
                .orElseThrow(() -> new ResourceNotFoundException("note_" + id));
    }
}
