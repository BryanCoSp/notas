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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
