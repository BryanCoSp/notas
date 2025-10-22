package com.notas.notas.controller;

import com.notas.notas.model.Note;
import com.notas.notas.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class NoteController {
    private NoteService noteService;

    @Autowired
    public void NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("notes", noteService.findAll());
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("noteID", noteService.findById(id));
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

    @DeleteMapping("/")
    public String delete(Note note) {
        noteService.delete(note);
        return "index";
    }

    @PutMapping("/")
    public String update(Note note) {
        noteService.save(note);
        return "index";
    }


}
