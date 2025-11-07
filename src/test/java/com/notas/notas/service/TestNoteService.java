package com.notas.notas.service;

import com.notas.notas.model.AppUser;
import com.notas.notas.model.Note;
import com.notas.notas.repository.AppUserRepository;
import com.notas.notas.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestNoteService {
    @Mock // Simula el repositorio
    private NoteRepository noteRepository;

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks // Inyecta los mocks creados en la clase service
    private NoteService noteService;

    @Test
    public void testGetNotes() {
        AppUser appUser = new AppUser();
        appUser.setUsername("test");

        // Arrange
        Note note1 = new Note();
        note1.setId(1);
        note1.setContent("Contenido de nota 1");
        note1.setOwner(appUser);

        Note note2 = new Note();
        note2.setId(2);
        note2.setContent("Contenido de nota 2");
        note2.setOwner(appUser);

        List<Note> notes = new ArrayList<>();
        notes.add(note1);
        notes.add(note2);

        when (noteRepository.findByOwnerUsername(appUser.getUsername())).thenReturn(notes);

        // Act
        List<Note> resultado = noteService.findAllFor(appUser.getUsername());

        // Assert
        assertEquals(2,  resultado.size());
        assertEquals("Contenido de nota 1", resultado.get(0).getContent());
        assertEquals("Contenido de nota 2", resultado.get(1).getContent());
        assertEquals("test", resultado.get(0).getOwner().getUsername());
        assertEquals("test", resultado.get(1).getOwner().getUsername());
    }

    @Test
    public void testGetNoteById() {
        AppUser appUser = new AppUser();
        appUser.setUsername("test");

        Note note = new Note();
        note.setId(1);
        note.setTitle("Titulo nota 1");
        note.setContent("Contenido de nota 1");
        note.setOwner(appUser);

        when(noteRepository.findByIdAndOwnerUsername(1L, appUser.getUsername())).thenReturn(Optional.of(note));

        Note res = noteService.findByIdFor(1L, appUser.getUsername()).get();

        assertEquals("Titulo nota 1", res.getTitle());
        assertEquals("Contenido de nota 1", res.getContent());
        assertEquals("test", res.getOwner().getUsername());
    }

    @Test
    public void testSaveNoteEmpty() {
        AppUser appUser = new AppUser();
        appUser.setUsername("test");

        Note note = new Note();
        note.setId(1L);
        note.setOwner(appUser);

        when(noteRepository.save(note)).thenReturn(note);
        when(noteRepository.findByIdAndOwnerUsername(1L, appUser.getUsername())).thenReturn(Optional.of(note));
        when(appUserRepository.findByUsername("test")).thenReturn(Optional.of(appUser));

        Note res1 = noteService.saveFor(note, appUser.getUsername());
        Note res2 = noteService.findByIdFor(1L, appUser.getUsername()).get();

        assertEquals(res1.getContent(), res2.getContent());
        assertEquals(res1.getTitle(), res2.getTitle());
        assertEquals(res1.getId(), res2.getId());
        assertEquals( "New note", res1.getTitle());
        assertNull(res2.getContent());
        assertEquals(res1.getOwner().getUsername(), res2.getOwner().getUsername());
    }

    @Test
    public void testSaveNote() {
        AppUser appUser = new AppUser();
        appUser.setUsername("test");

        Note note = new Note();
        note.setId(1L);
        note.setTitle("Titulo nota 1");
        note.setContent("Contenido de nota 1");
        note.setOwner(appUser);

        when(noteRepository.save(note)).thenReturn(note);
        when(appUserRepository.findByUsername("test")).thenReturn(Optional.of(appUser));

        Note res = noteService.saveFor(note, appUser.getUsername());

        assertEquals("Titulo nota 1", res.getTitle());
        assertEquals("Contenido de nota 1", res.getContent());
        assertEquals(1L, res.getId());
        assertEquals("test", res.getOwner().getUsername());
    }

    @Test
    public void testUpdateNote() {
        AppUser appUser = new AppUser();
        appUser.setUsername("test");

        Note note = new Note();
        note.setId(1L);
        note.setTitle("Titulo");
        note.setContent("Contenido de nota");
        note.setOwner(appUser);

        when(noteRepository.save(note)).thenReturn(note);
        when(appUserRepository.findByUsername("test")).thenReturn(Optional.of(appUser));

        note.setTitle("Titulo editado");
        note.setContent("Contenido de editado");

        Note res = noteService.updateFor(note, appUser.getUsername());

        assertEquals("Titulo editado", res.getTitle());
        assertEquals("Contenido de editado", res.getContent());
        assertEquals(1L, res.getId());
        assertEquals("test", res.getOwner().getUsername());
    }
}
