package com.shahinnazarov.noteapp.resource;

import com.shahinnazarov.noteapp.dto.ApiResponse;
import com.shahinnazarov.noteapp.dto.NoteQueryDto;
import com.shahinnazarov.noteapp.dto.NoteUpdateDto;
import com.shahinnazarov.noteapp.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/notes", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class NoteResource {
    private final NoteService noteService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<NoteQueryDto>> save(@Valid @RequestBody final NoteUpdateDto noteUpdateDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(noteService.save(noteUpdateDto)));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<NoteQueryDto>> update(@PathVariable String id,
                                                            @Valid @RequestBody final NoteUpdateDto noteUpdateDto) {
        return ResponseEntity.ok(ApiResponse.success(noteService.update(id, noteUpdateDto)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ApiResponse<NoteQueryDto>> update(@PathVariable String id) {
        noteService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.success());
    }

}
