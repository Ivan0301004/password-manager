package com.ivan.passwordmanager.service;

import com.ivan.passwordmanager.dto.NoteDto;
import com.ivan.passwordmanager.model.Note;

import java.util.List;

public interface NoteService {

    List<NoteDto> getAllNotes();

    Note createNoteToSiteById(NoteDto noteDto, Long siteId);

    void removeNoteFromSiteById(Long noteId, Long siteId);

    NoteDto updateNote(Long id, NoteDto noteDto);

}
