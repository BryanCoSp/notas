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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        mockMvc.perform(post("/submit").with(user("testUser").roles("USER"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON).content(newNoteJson))
                .andExpect(status().is3xxRedirection());

//        mockMvc.perform(get("/1").with(user("testUser").roles("USER"))
//                .with(csrf())).andExpect(status().isOk());
    }
}
