package com.example.easynotes.controller;


import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import com.example.easynotes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
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
        //List<Note> notesList = noteRepository.findAll();
        model.addAttribute("noteList", noteRepository.findAll());
        return "notes";
    }

//    @PostMapping("/notes")
    @RequestMapping(value="/notes",
            method=RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void createNote(Note note, HttpServletResponse response) throws IOException {
        noteRepository.save(note);
        response.sendRedirect("/api/notes");
    }

    @GetMapping("/notes/{id}")
    public String getNoteById(@PathVariable(value = "id") Integer noteId, Model model) {
        Optional<Note> tempNote = noteRepository.findById(noteId);
        model.addAttribute("thenote", tempNote.get());
        return "note";
    }

    @RequestMapping(value="/notes/{id}",
            method=RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void updateNote(@PathVariable(value = "id") Integer noteId, Note noteDetails, HttpServletResponse response) throws IOException {
        Optional<Note> noteOptional = noteRepository.findById(noteId);

        if (noteOptional.isPresent()) {
            Note note = noteOptional.get();
            note.setName(noteDetails.getName());
            note.setText(noteDetails.getText());

            Note updatedNote = noteRepository.save(note);
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
            noteRepository.delete(note);

            return ResponseEntity.ok().build();
        } else {
            return null;
        }
    }

    @GetMapping("/createNote")
    public String create() {

        return "createNote";
    }

}
