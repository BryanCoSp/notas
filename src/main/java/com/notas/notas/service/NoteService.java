package com.notas.notas.service;

import com.notas.notas.model.Note;
import com.notas.notas.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List<Note> findAll() {
        List<Note> notes = noteRepository.findAll();
        for  (Note note : notes) {
            if (note.getContent().length() > 300) {
                note.setContent(note.getContent().substring(0, 300));
            }
        }
        return notes;
    }

    public Optional<Note> findById(@PathVariable Long id) {
        return noteRepository.findById(id);
    }

    public Note save(Note note) {
        if(note.getTitle() == null || note.getTitle().isEmpty()){
            note.setTitle("New note");
        }
        return noteRepository.save(note);
    }

    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

    public Note update(Note note) {
        return noteRepository.save(note);
    }

}
