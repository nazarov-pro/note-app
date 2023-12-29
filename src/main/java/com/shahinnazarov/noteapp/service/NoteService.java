package com.shahinnazarov.noteapp.service;

import com.shahinnazarov.noteapp.dto.NoteQueryDto;
import com.shahinnazarov.noteapp.dto.NoteUpdateDto;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface NoteService {

    NoteQueryDto save(NoteUpdateDto noteUpdateDto);

    NoteQueryDto update(String id, NoteUpdateDto noteUpdateDto);

    void delete(String id);

    Collection<NoteQueryDto> list(Pageable pageable);

    NoteQueryDto get(String id);
}
