package com.example.easynotes.service;

import com.example.easynotes.model.Note;
import com.example.easynotes.model.dto.NoteDTO;
import com.example.easynotes.repository.NoteRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteService {
    private static List<Note> notes = new ArrayList<Note>();

    @Autowired
    private NoteRepository repo;

    @Autowired
    private ModelMapper modelMapper;


    public List<NoteDTO> retrieveNotes() {

        return ((List<Note>) repo
                .findAll())
                .stream()
                .map(this::convertToNoteDTO)
                .collect(Collectors.toList());
    }

    public Note createNote(Note note) {

        return repo.save(note);

    }

    public Optional<Note> findNote(int id) {
        return repo.findById(id);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public NoteDTO convertToNoteDTO(Note note) {

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        NoteDTO notedto = modelMapper.map(note, NoteDTO.class);
        return notedto;
    }

    public Note convertToNote(NoteDTO notedto) {

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Note note = modelMapper.map(notedto, Note.class);
        return note;
    }




}
