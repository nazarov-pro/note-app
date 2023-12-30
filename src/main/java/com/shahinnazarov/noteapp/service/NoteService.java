package com.shahinnazarov.noteapp.service;

import com.shahinnazarov.noteapp.dto.NoteQueryDto;
import com.shahinnazarov.noteapp.dto.NoteUpdateDto;
import com.shahinnazarov.noteapp.entity.enums.Tags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Set;

public interface NoteService {

    NoteQueryDto save(NoteUpdateDto noteUpdateDto);

    NoteQueryDto update(String id, NoteUpdateDto noteUpdateDto);

    void delete(String id);

    Page<NoteQueryDto> list(boolean minimalResponse, String tags, PageRequest pageRequest);

    NoteQueryDto get(String id);
}
