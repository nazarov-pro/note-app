package com.shahinnazarov.noteapp.resource;

import com.shahinnazarov.noteapp.dto.NoteQueryDto;
import com.shahinnazarov.noteapp.dto.NoteUpdateDto;
import com.shahinnazarov.noteapp.service.NoteService;
import com.shahinnazarov.noteapp.utils.JsonUtils;
import com.shahinnazarov.noteapp.utils.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteResource.class)
public class NoteResourceTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private NoteService noteService;

    @Test
    void whenListThenFindAllAndPaginate() throws Exception {
        final boolean minimal = true;
        final String tags = "BUSINESS";
        final PageRequest pageRequest = PageRequest.of(0, 10);

        final NoteQueryDto queryDto = JsonUtils.asObject(getResourceStream("simple-single-note-response.json"),
                NoteQueryDto.class);
        when(noteService.list(minimal, tags, pageRequest))
                .thenReturn(new PageImpl<>(List.of(queryDto), pageRequest, 1));

        this.mockMvc.perform(get("/notes")
                        .queryParam("minimal", String.valueOf(minimal))
                        .queryParam("tags", tags)
                        .queryParam("size", String.valueOf(pageRequest.getPageSize()))
                        .queryParam("page", String.valueOf(pageRequest.getPageNumber())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items", content().json(JsonUtils.asString(queryDto))).exists())
                .andExpect(jsonPath("$.total").value(1))
                .andExpect(jsonPath("$.page_size").value(pageRequest.getPageSize()))
                .andExpect(jsonPath("$.page_number").value(pageRequest.getPageNumber()));
    }

    @Test
    void givenIdWhenGetByIdThenFindById() throws Exception {
        final String id = UUID.randomUUID().toString();
        final NoteQueryDto queryDto = JsonUtils.asObject(getResourceStream("simple-single-note-response.json"),
                NoteQueryDto.class);
        when(noteService.get(eq(id))).thenReturn(queryDto);

        this.mockMvc.perform(get("/notes/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.item", content().json(JsonUtils.asString(queryDto))).exists());
    }

    @Test
    void givenIdWhenGetIdThenThrowNotFound() throws Exception {
        final String id = UUID.randomUUID().toString();
        final String exceptionDetails = "note_" + id;

        when(noteService.get(eq(id))).thenThrow(new ResourceNotFoundException(exceptionDetails));

        this.mockMvc.perform(get("/notes/" + id))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error_details.resource_name", content().string(exceptionDetails)).exists());
    }

    @Test
    void givenValidRequestWhenSaveThenReturnDto() throws Exception {
        final String body = StreamUtils.copyToString(getResourceStream("valid-note.json"),
                Charset.defaultCharset());
        final NoteQueryDto queryDto = JsonUtils.asObject(getResourceStream("simple-single-note-response.json"),
                NoteQueryDto.class);

        when(noteService.save(any())).thenReturn(queryDto);

        this.mockMvc.perform(post("/notes").content(body).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.item", content().json(JsonUtils.asString(queryDto))).exists());
    }

    @Test
    void givenInValidRequestWithBlankTitleWhenSaveThenThrowException() throws Exception {
        final String body = StreamUtils.copyToString(getResourceStream("invalid-note-blank-title.json"),
                Charset.defaultCharset());

        this.mockMvc.perform(post("/notes").content(body).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error_details.title", content()
                        .string("Title is mandatory")).exists());
    }

    @Test
    void givenInValidRequestWithContentTitleWhenSaveThenThrowException() throws Exception {
        final String body = StreamUtils.copyToString(getResourceStream("invalid-note-blank-content.json"),
                Charset.defaultCharset());

        this.mockMvc.perform(post("/notes").content(body).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error_details.content", content()
                        .string("Content is mandatory")).exists());
    }

    @Test
    void givenValidRequestWhenUpdateThenReturnDto() throws Exception {
        final String body = StreamUtils.copyToString(getResourceStream("valid-note.json"),
                Charset.defaultCharset());
        final NoteQueryDto queryDto = JsonUtils.asObject(getResourceStream("simple-single-note-response.json"),
                NoteQueryDto.class);

        when(noteService.update(eq("id"), any())).thenReturn(queryDto);

        this.mockMvc.perform(put("/notes/id").content(body).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.item", content().json(JsonUtils.asString(queryDto))).exists());
    }

    @Test
    void givenInValidRequestWithBlankTitleAndInvalidTagWhenUpdateThenThrowException() throws Exception {
        final String body = StreamUtils.copyToString(getResourceStream("invalid-note-unsupported-tag-and-blank-title.json"),
                Charset.defaultCharset());

        this.mockMvc.perform(put("/notes/id").content(body).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error_details.title", content()
                        .string("Title is mandatory")).exists())
                .andExpect(jsonPath("$.error_details.tags", content()
                        .string("Invalid tag provided")).exists());

    }

    @Test
    void givenValidIdWhenDeleteThenReturnOk() throws Exception {
        final String id = UUID.randomUUID().toString();

        doNothing().when(noteService).delete(eq(id));

        this.mockMvc.perform(delete("/notes/id"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.message", content().json("ok")).exists());
    }

    private InputStream getResourceStream(final String path) {
        return getClass().getClassLoader().getResourceAsStream(path);
    }
}
