package com.notas.notas.service;

import com.notas.notas.model.Note;
import com.notas.notas.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private NoteRepository noteRepository;

    @Autowired
    public void NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Note> findById(@PathVariable Long id) {
        return noteRepository.findById(id);
    }
}
