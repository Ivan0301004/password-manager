package com.ivan.passwordmanager.controllers;

import com.ivan.passwordmanager.dto.NoteDto;
import com.ivan.passwordmanager.model.Note;
import com.ivan.passwordmanager.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/users/notes")
    public ResponseEntity<List<NoteDto>> getAllNotes() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(this.noteService.getAllNotes());
    }

    @PostMapping("/users/site/{siteId}/notes")
    public ResponseEntity<Note> createNoteToSite(@RequestBody NoteDto noteDto, @PathVariable long siteId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.noteService.createNoteToSiteById(noteDto, siteId));
    }

    @DeleteMapping("/users/site/{siteId}/note/{noteId}")
    public ResponseEntity<NoteDto> deleteNoteFromSite(@PathVariable long noteId, @PathVariable long siteId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(this.noteService.removeNoteFromSiteById(noteId, siteId));

    }

    @PutMapping("/users/site/{siteId}/note/{noteId}")
    public ResponseEntity<NoteDto> updateNote(@PathVariable long noteId, @RequestBody NoteDto noteDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(this.noteService.updateNote(noteId, noteDto));
    }

}
