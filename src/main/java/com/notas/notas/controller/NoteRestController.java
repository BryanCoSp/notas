package com.notas.notas.controller;

import com.notas.notas.model.Note;
import com.notas.notas.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@RestController
//@RequestMapping("/api")
public class NoteRestController {
//    private NoteService noteService;
//
//    @Autowired
//    public void NoteController(NoteService noteService) {
//        this.noteService = noteService;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Note> getNote(@RequestParam Long id) {
//        Optional<Note> note = noteService.findById(id);
//        return ResponseEntity.ok(note.get());
//    }
//
//    @PostMapping("/save/{id}")
//    public ResponseEntity<Note> saveNoteID(@RequestBody Note note) {
//        Note savedNote = noteService.save(note);
//        return ResponseEntity.ok(savedNote);
//    }
//
//    @PostMapping("/save")
//    public ResponseEntity<Note> saveNote(@RequestBody Note note) {
//        Note savedNote = noteService.save(note);
//        return ResponseEntity.ok(savedNote);
//    }
}