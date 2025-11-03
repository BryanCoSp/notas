package com.notas.notas.service;

import com.notas.notas.model.Note;
import com.notas.notas.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestNoteService {
    @Mock // Simula el repositorio
    private NoteRepository noteRepository;

    @InjectMocks // Inyecta los mocks creados en la clase service
    private NoteService noteService;

    @Test
    public void testGetNotes() {
        // Arrange
        Note note1 = new Note();
        note1.setId(1);
        note1.setContent("Contenido de nota 1");

        Note note2 = new Note();
        note2.setId(2);
        note2.setContent("Contenido de nota 2");

        List<Note> notes = new ArrayList<>();
        notes.add(note1);
        notes.add(note2);

        when (noteRepository.findAll()).thenReturn(notes);

        // Act
        List<Note> resultado = noteService.findAll();

        // Assert
        assertEquals(2,  resultado.size());
        assertEquals("Contenido de nota 1", resultado.get(0).getContent());
        assertEquals("Contenido de nota 2", resultado.get(1).getContent());
    }

    @Test
    public void testGetNoteById() {
        Note note = new Note();
        note.setId(1);
        note.setTitle("Titulo nota 1");
        note.setContent("Contenido de nota 1");

        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));

        Note res = noteService.findById(1L).get();

        assertEquals("Titulo nota 1", res.getTitle());
        assertEquals("Contenido de nota 1", res.getContent());
    }

    @Test
    public void testSaveNoteEmpty() {
        Note note = new Note();
        note.setId(1L);

        when(noteRepository.save(note)).thenReturn(note);
        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));

        Note res1 = noteService.save(note);
        Note res2 = noteService.findById(1L).get();

        assertEquals(res1.getContent(), res2.getContent());
        assertEquals(res1.getTitle(), res2.getTitle());
        assertEquals(res1.getId(), res2.getId());
        assertEquals( "New note", res1.getTitle());
        assertNull(res2.getContent());
    }

    @Test
    public void testSaveNote() {
        Note note = new Note();
        note.setId(1L);
        note.setTitle("Titulo nota 1");
        note.setContent("Contenido de nota 1");

        when(noteRepository.save(note)).thenReturn(note);

        Note res = noteService.save(note);

        assertEquals("Titulo nota 1", res.getTitle());
        assertEquals("Contenido de nota 1", res.getContent());
        assertEquals(1L, res.getId());
    }
}
