package com.notas.notas.controller;

import com.notas.notas.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
