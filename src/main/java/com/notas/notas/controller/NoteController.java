package com.notas.notas.controller;

import com.notas.notas.exception.NoteNotFoundException;
import com.notas.notas.model.Note;
import com.notas.notas.service.NoteService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("notes", noteService.findAllFor(user.getUsername()));
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("note", noteService.findByIdFor(id, user.getUsername()).orElseThrow(() -> new NoteNotFoundException(id)));
        return "showNote";
    }

    @GetMapping("/newNote")
    public String getNewNoteFragment(Model model) {
        return "fragments/newNote :: popup";
    }

    @PostMapping("/submit")
    public String save(@ModelAttribute Note note, @AuthenticationPrincipal User user) {
        noteService.saveFor(note, user.getUsername());
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(Long id,  @AuthenticationPrincipal User user) {
        noteService.deleteFor(id, user.getUsername());
        return "redirect:/";
    }

    @PostMapping("/updateNote")
    public String update(Note note,  @AuthenticationPrincipal User user) {
        noteService.saveFor(note, user.getUsername());
        return "redirect:/";
    }


}
