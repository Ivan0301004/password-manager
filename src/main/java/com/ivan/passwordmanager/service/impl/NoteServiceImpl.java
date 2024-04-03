package com.ivan.passwordmanager.service.impl;

import com.ivan.passwordmanager.dto.NoteDto;
import com.ivan.passwordmanager.mappers.NoteMapper;
import com.ivan.passwordmanager.model.Note;
import com.ivan.passwordmanager.repository.NoteRepository;
import com.ivan.passwordmanager.service.NoteService;

import java.util.List;

public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    public NoteServiceImpl(NoteRepository noteRepository, NoteMapper noteMapper) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
    }

    @Override
    public List<NoteDto> getAllNotes() {
        return null;
    }

    @Override
    public Note createNote(Note note) {
        return null;
    }

    @Override
    public void removeNoteById(Long id) {

    }

    @Override
    public Note updateNote(Long id, NoteDto noteDto) {
        return null;
    }

}
