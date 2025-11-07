package com.notas.notas.controller;

import com.notas.notas.model.AppUser;
import com.notas.notas.model.Note;
import com.notas.notas.repository.AppUserRepository;
import com.notas.notas.repository.NoteRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
public class TestNoteController {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AppUserRepository appUserRepository;

    @MockitoBean
    private NoteRepository noteRepository;

    @Test
    public void saveNoteTest() throws Exception {
        String newNoteJson = "{\"title\": \"Note\", \"content\": \"hola\"}";

        AppUser user = new AppUser();
        user.setUsername("test2");
        user.setId(1L);

        when(appUserRepository.findByUsername("test")).thenReturn(Optional.of(user));

        mockMvc.perform(post("/submit")
                        .with(user("test").roles("USER"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newNoteJson))
                .andExpect(status().is3xxRedirection())
                .andExpect(result -> result.getResponse().getContentAsString().contains("hola"));
    }

    @Test
    public void getUserNotesTest() throws Exception {
        AppUser user = new AppUser();
        user.setUsername("test");
        user.setId(1L);

        Note note1 =  new Note();
        note1.setId(1);
        note1.setTitle("Note1");
        note1.setContent("content 1");

        when(appUserRepository.findByUsername("test")).thenReturn(Optional.of(user));
        when(noteRepository.findByOwnerUsername(user.getUsername())).thenReturn(Arrays.asList(note1));
        when(noteRepository.findAll()).thenReturn(Arrays.asList(note1));

        mockMvc.perform(get("/")
                        .with(user("test").roles("USER"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String content = result.getResponse().getContentAsString();
                    assertTrue(content.contains("Note1"));
                    assertTrue(content.contains("content 1"));
                });
    }

    @Test
    public void deleteNoteTest() throws Exception {
        String newNoteJson = "{\"title\": \"Note\", \"content\": \"hola\"}";
        String idJson = "{\"id\":1}";

        AppUser user = new AppUser();
        user.setUsername("test2");
        user.setId(1L);

        when(appUserRepository.findByUsername("test")).thenReturn(Optional.of(user));

        mockMvc.perform(post("/submit")
                        .with(user("test").roles("USER"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newNoteJson)
        );

        mockMvc.perform(delete("/delete")
                        .with(user("test").roles("USER"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(idJson))
                .andExpect(status().is3xxRedirection())
                .andExpect(result -> {
                    String content = result.getResponse().getContentAsString();
                    assertFalse(content.contains("hola"), "El texto 'hola' no debería estar presente en el HTML");
                    assertFalse(content.contains("Note"), "El texto 'Note' no debería estar presente en el HTML");
                });
    }


    @Test
    public void showUserNoteTest() throws Exception {
        AppUser user = new AppUser();
        user.setUsername("test");
        user.setId(1L);

        Note note1 =  new Note();
        note1.setId(1);
        note1.setTitle("Note1");
        note1.setContent("content 1");

        when(appUserRepository.findByUsername("test")).thenReturn(Optional.of(user));
        when(noteRepository.findByIdAndOwnerUsername(note1.getId(), user.getUsername())).thenReturn(Optional.of(note1));

        mockMvc.perform(get("/1")
                        .with(user("test").roles("USER"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String content = result.getResponse().getContentAsString();
                    assertTrue(content.contains("Note1"));
                    assertTrue(content.contains("content 1"));
                    assertTrue(content.contains("Update Note"));
                });
    }

    @Test
    public void getNewNoteFragmentTest() throws Exception {
        AppUser user = new AppUser();
        user.setUsername("test");
        user.setId(1L);

        Note note1 =  new Note();
        note1.setId(1);
        note1.setTitle("Note1");
        note1.setContent("content 1");

        when(appUserRepository.findByUsername("test")).thenReturn(Optional.of(user));

        mockMvc.perform(get("/newNote")
                        .with(user("test").roles("USER"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String content = result.getResponse().getContentAsString();
                    assertTrue(content.contains("Add note"));
                });
    }
}

