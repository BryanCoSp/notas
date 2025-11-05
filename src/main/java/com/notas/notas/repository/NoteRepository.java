package com.notas.notas.repository;

import com.notas.notas.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByOwnerUsername(String username);

    Optional<Note> findByIdAndOwnerUsername(Long id, String username);

    void deleteByIdAndOwnerUsername(Long id, String username);
}
