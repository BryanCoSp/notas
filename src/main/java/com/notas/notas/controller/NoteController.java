package com.notas.notas.controller;

import com.notas.notas.exception.NoteNotFoundException;
import com.notas.notas.model.Note;
import com.notas.notas.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("notes", noteService.findAll());
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("note", noteService.findById(id).orElseThrow());
        return "showNote";
    }

    @GetMapping("/newNote")
    public String obtenerListaNotas(Model model) {
        return "fragments/newNote :: popup";
    }

    @PostMapping("/submit")
    public String save(@ModelAttribute Note note) {
        noteService.save(note);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(Long id) {
        noteService.delete(id);
        return "redirect:/";
    }

    @PostMapping("/updateNote")
    public String update(Note note) {
        noteService.save(note);
        return "redirect:/";
    }


}
