package com.ivan.passwordmanager.service;

import com.ivan.passwordmanager.dto.NoteDto;
import com.ivan.passwordmanager.model.Note;

import java.util.List;

public interface NoteService {

    List<NoteDto> getAllNotes();

    Note createNote(Note note);

    void removeNoteById(Long id);

    Note updateNote(Long id, NoteDto noteDto);

}
