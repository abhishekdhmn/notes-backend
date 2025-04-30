package com.notes.notes_app.controller;


import com.notes.notes_app.model.Notes;
import com.notes.notes_app.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    NotesService notesService;

    @GetMapping("/getnotes")
    public List<Notes> getNotes() {
        return notesService.getNotes();
    }

    @PostMapping("/addnote")
    public Notes addNote(@RequestBody Notes note) {
        return notesService.addNote(note);
    }

    @PutMapping("/updatenote")
    public Notes updateNote(@RequestBody Notes note){
        return notesService.updateNote(note);
    }

    @DeleteMapping("/deletenote/{id}")
    public void deleteNote(@PathVariable Long id) {
         notesService.deleteNote(id);
    }
}
