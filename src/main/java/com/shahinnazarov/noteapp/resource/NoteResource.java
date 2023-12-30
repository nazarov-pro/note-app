package com.shahinnazarov.noteapp.resource;

import com.shahinnazarov.noteapp.dto.ApiResponse;
import com.shahinnazarov.noteapp.dto.NoteQueryDto;
import com.shahinnazarov.noteapp.dto.NoteUpdateDto;
import com.shahinnazarov.noteapp.entity.enums.Tags;
import com.shahinnazarov.noteapp.service.NoteService;
import com.shahinnazarov.noteapp.utils.validators.NoteTagAsStringConstraint;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/notes", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class NoteResource {
    private final NoteService noteService;

    @Validated
    @GetMapping
    public ResponseEntity<ApiResponse<NoteQueryDto>> list(
            @RequestParam(name = "minimal", defaultValue = "false") final boolean minimalResponse,
            @NoteTagAsStringConstraint @RequestParam(name = "tags", defaultValue = "") final String tags,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        final PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(ApiResponse.success(noteService.list(minimalResponse, tags, pageRequest)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NoteQueryDto>> get(@PathVariable final String id) {
        return ResponseEntity.ok(ApiResponse.success(noteService.get(id)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<NoteQueryDto>> save(@Valid @RequestBody final NoteUpdateDto noteUpdateDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(noteService.save(noteUpdateDto)));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<NoteQueryDto>> update(@PathVariable final String id,
                                                            @Valid @RequestBody final NoteUpdateDto noteUpdateDto) {
        return ResponseEntity.ok(ApiResponse.success(noteService.update(id, noteUpdateDto)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ApiResponse<NoteQueryDto>> delete(@PathVariable final String id) {
        noteService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.success());
    }

}
