package com.ivan.passwordmanager.controllers;

import com.ivan.passwordmanager.service.NoteService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

}
