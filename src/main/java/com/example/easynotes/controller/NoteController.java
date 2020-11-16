package com.example.easynotes.controller;


import com.example.easynotes.model.Note;
import com.example.easynotes.model.dto.NoteDTO;
import com.example.easynotes.repository.NoteRepository;
import com.example.easynotes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;
    @Autowired
    NoteService service;



    @ModelAttribute("note")
    public Note defaultInstance() {
        Note note = new Note();
        return note;
    }

    @GetMapping("/notes")
    public String getAllNotes(Model model) {
        model.addAttribute("noteList", service.retrieveNotes());
        System.out.println("notes retrieved.");
        return "notes";
    }

    @RequestMapping(value="/notes",
            method=RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void createNote(NoteDTO notedto, HttpServletResponse response) throws IOException {
        Note note = service.convertToNote(notedto);
        service.createNote(note);
        System.out.println("note with id: " + notedto.getId() + " created.");
        response.sendRedirect("/api/notes");
    }

    @GetMapping("/notes/{id}")
    public String getNoteById(@PathVariable(value = "id") Integer noteId, Model model) {
        Optional<Note> note = service.findNote(noteId);
        model.addAttribute("thenote", note.get());
        System.out.println("note with id: " + noteId + " retrieved.");
        return "note";
    }

    @RequestMapping(value="/notes/{id}",
            method=RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void updateNote(@PathVariable(value = "id") Integer noteId, NoteDTO notedto, HttpServletResponse response) throws IOException {
        Optional<Note> noteOptional = service.findNote(noteId);

        if (noteOptional.isPresent()) {
            Note note = noteOptional.get();

            Note noteNew = service.convertToNote(notedto);
            note.setName(noteNew.getName());
            note.setText(noteNew.getText());
            service.createNote(note);
            System.out.println("note with id: " + noteId + " updated.");
            response.sendRedirect("/api/notes");
        } else {
           // return null;
        }


    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Integer noteId) {
        Optional<Note> noteOptional = noteRepository.findById(noteId);

        if (noteOptional.isPresent()) {
            Note note = noteOptional.get();
            service.delete(note.getId());
            System.out.println("note with id: " + noteId + " deleted.");
            return ResponseEntity.ok().body("note with id: " + noteId + " deleted.");
        } else {
            return null;
        }
    }

    @GetMapping("/createNote")
    public String create() {

        return "createNote";
    }

}
