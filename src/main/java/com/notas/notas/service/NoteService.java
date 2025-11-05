package com.notas.notas.service;

import com.notas.notas.exception.ServiceTechnicalException;
import com.notas.notas.model.AppUser;
import com.notas.notas.model.Note;
import com.notas.notas.repository.AppUserRepository;
import com.notas.notas.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final AppUserRepository appUserRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository, AppUserRepository appUserRepository) {
        this.noteRepository = noteRepository;
        this.appUserRepository = appUserRepository;
    }

    public List<Note> findAllFor(String username) {
        List<Note> notes = noteRepository.findByOwnerUsername(username);
        for (Note note : notes) {
            if (note.getContent().length() > 300) {
                note.setContent(note.getContent().substring(0, 300));
            }
        }
        return notes;
    }

    public Optional<Note> findByIdFor(@PathVariable Long id, String username) {
        return noteRepository.findByIdAndOwnerUsername(id, username);
    }

    public Note saveFor(Note note, String username) {
        if (note.getTitle() == null || note.getTitle().isEmpty()) {
            note.setTitle("New note");
        }
        try {
            AppUser user = appUserRepository.findByUsername(username).get();
            note.setOwner(user);
            return noteRepository.save(note);
        } catch (Exception e) {
            throw new ServiceTechnicalException(e.getMessage());
        }

    }

    @Transactional
    public void deleteFor(Long id, String username) {
        noteRepository.deleteByIdAndOwnerUsername(id, username);
    }

    public Note updateFor(Note note, String username) {
        try {
            AppUser user = appUserRepository.findByUsername(username).get();
            note.setOwner(user);
            return noteRepository.save(note);
        } catch (Exception e) {
            throw new ServiceTechnicalException(e.getMessage());
        }
    }

}
