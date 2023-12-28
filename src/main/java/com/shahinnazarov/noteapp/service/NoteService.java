package com.shahinnazarov.noteapp.service;

import com.shahinnazarov.noteapp.dto.NoteQueryDto;
import com.shahinnazarov.noteapp.dto.NoteUpdateDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface NoteService {

    void save(NoteUpdateDto noteUpdateDto);

    void update(NoteUpdateDto noteUpdateDto);

    void delete(String id);

    Collection<NoteQueryDto> list(Pageable pageable);

    NoteQueryDto get(String id);
}
