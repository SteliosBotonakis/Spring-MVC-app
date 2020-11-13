package com.example.easynotes.service;

import com.example.easynotes.model.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {
    private static List<Note> notes = new ArrayList<Note>();

    public List<Note> retrieveNotes() {
        return notes;
    }
}
