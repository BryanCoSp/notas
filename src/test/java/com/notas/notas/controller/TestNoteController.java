package com.notas.notas.controller;

import com.notas.notas.model.Note;
import com.notas.notas.repository.NoteRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class TestNoteController {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void saveNoteTest() throws Exception {
        String newNoteJson = "{\"title\": \"Note\", \"content\": \"hola\"}";
        mockMvc.perform(post("/submit").contentType(MediaType.APPLICATION_JSON).content(newNoteJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Note"))
                .andExpect(jsonPath("$.content").value("hola"))
                .andExpect(jsonPath("$.id").exists());
    }
}
